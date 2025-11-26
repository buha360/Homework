import { Component } from '@angular/core';
import { VideoListComponent } from './features/video-list/video-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [VideoListComponent],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {}
