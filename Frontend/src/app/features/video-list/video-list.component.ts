import { Component, OnInit } from '@angular/core';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { VideoService } from '../../services/video.service';
import { Video } from '../../models/video.model';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-video-list',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './video-list.component.html',
  styleUrl: './video-list.component.css',
})
export class VideoListComponent implements OnInit {
  videos$: Observable<Video[]> = of([]);
  loading$ = new BehaviorSubject(false);
  error$ = new BehaviorSubject<string | null>(null);

  constructor(private videoService: VideoService) {}

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): void {
    this.loading$.next(true);
    this.error$.next(null);

    this.videos$ = this.videoService.getAll().pipe(
      tap(() => this.loading$.next(false)),
      catchError((err) => {
        console.error('Error loading videos:', err);
        this.error$.next('Failed to load videos');
        this.loading$.next(false);
        return of([]);
      })
    );
  }
}
