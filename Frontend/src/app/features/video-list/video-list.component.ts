import { Component, OnInit } from '@angular/core';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { VideoService } from '../../services/video.service';
import { Video } from '../../models/video.model';
import { AsyncPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-video-list',
  standalone: true,
  imports: [AsyncPipe, FormsModule],
  templateUrl: './video-list.component.html',
  styleUrl: './video-list.component.css',
})
export class VideoListComponent implements OnInit {
  videos$: Observable<Video[]> = of([]);
  loading$ = new BehaviorSubject(false);
  error$ = new BehaviorSubject<string | null>(null);
  selectedVideo: Video | null = null;
  selectedVideoSafeUrl: SafeResourceUrl | null = null;

  shouldScroll = false;
  private readonly SCROLL_THRESHOLD = 7;

  // Filter
  filters = {
    title: '',
    genre: '',
    minImdbRating: null as number | null,
    releaseYearFrom: null as number | null,
    releaseYearTo: null as number | null,
  };

  constructor(private videoService: VideoService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): void {
    this.loading$.next(true);
    this.error$.next(null);

    this.videos$ = this.videoService.getAll().pipe(
      tap((videos) => {
        this.loading$.next(false);
        this.shouldScroll = videos.length >= this.SCROLL_THRESHOLD;
      }),
      catchError((err) => {
        console.error('Error loading videos:', err);
        this.error$.next('Failed to load videos');
        this.loading$.next(false);
        this.shouldScroll = false;
        return of([]);
      })
    );
  }

  applyFilters(): void {
    const params: any = {
      title: this.filters.title?.trim() || undefined,
      genre: this.filters.genre?.trim() || undefined,
      minImdbRating: this.filters.minImdbRating ?? undefined,
      releaseYearFrom: this.filters.releaseYearFrom ?? undefined,
      releaseYearTo: this.filters.releaseYearTo ?? undefined,
    };

    this.loading$.next(true);
    this.error$.next(null);

    this.videos$ = this.videoService.search(params).pipe(
      tap((videos) => {
        this.loading$.next(false);
        this.shouldScroll = videos.length >= this.SCROLL_THRESHOLD;
      }),
      catchError((err) => {
        console.error('Error searching videos:', err);
        this.error$.next('Failed to search videos');
        this.loading$.next(false);
        this.shouldScroll = false;
        return of([]);
      })
    );
  }

  // Clear button
  clearFilters(): void {
    this.filters = {
      title: '',
      genre: '',
      minImdbRating: null,
      releaseYearFrom: null,
      releaseYearTo: null,
    };
    this.loadAll();
  }

  openPreview(video: Video): void {
    if (!video.introVideoUrl) {
      return;
    }
    const embedUrl = this.buildYoutubeEmbedUrl(video.introVideoUrl);
    this.selectedVideo = video;
    this.selectedVideoSafeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(embedUrl);
  }

  closePreview(): void {
    this.selectedVideo = null;
    this.selectedVideoSafeUrl = null;
  }

  private buildYoutubeEmbedUrl(url: string): string {
    try {
      const u = new URL(url);
      let id = '';

      if (u.hostname.includes('youtu.be')) {
        id = u.pathname.replace('/', '');
      } else if (u.searchParams.has('v')) {
        id = u.searchParams.get('v') ?? '';
      } else if (u.pathname.includes('/embed/')) {
        id = u.pathname.split('/embed/')[1];
      }

      if (!id) {
        return url;
      }

      return `https://www.youtube.com/embed/${id}?autoplay=1`;
    } catch {
      return url;
    }
  }
}
