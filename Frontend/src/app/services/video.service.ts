import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Video } from '../models/video.model';

@Injectable({
  providedIn: 'root',
})
export class VideoService {
  private readonly baseUrl = 'http://localhost:8080/api/videos';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Video[]> {
    return this.http.get<Video[]>(`${this.baseUrl}/all`);
  }

  search(params: {
    title?: string;
    category?: string;
    genre?: string;
    language?: string;
    maturityRating?: string;
    minImdbRating?: number;
    maxImdbRating?: number;
    releaseYearFrom?: number;
    releaseYearTo?: number;
  }): Observable<Video[]> {
    let httpParams = new HttpParams();
    Object.entries(params).forEach(([key, value]) => {
      if (value !== undefined && value !== null && value !== '') {
        httpParams = httpParams.set(key, String(value));
      }
    });

    return this.http.get<Video[]>(this.baseUrl, { params: httpParams });
  }
}
