(A kiindulási feladat leírása videófelvételekhez kapcsolódó metaadat-alapú lekérdező rendszert írt elő. Ezt a koncepciót első olvasásra félreérelmeztem, ezután kreatívan újragondoltam: a videószekvenciák nálam filmekhez és trailerekhez tartoznak, amelyek szintén metaadatokkal (cím, műfaj, kategória, év, nyelv, értékelés stb.) rendelkeznek.)
# Working Hours - ⏱️
The development of this project was completed within a focused three-day workflow:

Day 1 – Monday (4–5 hours):
Initial setup of the full environment, including Angular frontend, Spring Boot backend, and the Oracle database running in Docker.

Day 2 – Tuesday (6–8 hours):
Implementation of the initial backend structure and the full database schema, including entities, repositories, and configuration.

Day 3 – Wednesday (8–10 hours):
Extension of backend features and full implementation of the frontend UI, including search filters, preview components, animated marquee row, and trailer popup window.

After Wednesday, development was paused due to the lack of response regarding additional details or refinement directions.


# Movie Searcher - 🎬

This project is a modern, responsive movie browsing interface built with **Angular 21**, **Spring Boot** backend, and an **Oracle Database running inside Docker**.  
It features real-time filtering, animated previews, and a clean minimalist UI inspired by popular streaming platforms.

To run this project: **docker compose up --build** ------ http://localhost:4200/

![Preview](./pictures/1.png)

### The database is running inside a docker.

![Preview](./pictures/4.png)

## Hover Animation (Interactive Movie Cards)

Each movie is displayed as an animated card that reacts to user interaction:

- Smooth **hover lift effect**
- Deepened shadow for emphasis
- Instant, responsive animation
- Gives the UI a premium, polished feeling

## Infinite Marquee Scrolling Row

The homepage contains a dynamic **horizontal marquee** that continuously scrolls movie cards:

- Cards smoothly move from right → left
- Animation **pauses on hover**
- Automatic duplication of items for a seamless infinite loop
- If the result list is short, the marquee automatically disables itself

## Trailer Preview Modal (YouTube Autoplay)

Clicking on any movie card opens a centered **1280×720 trailer preview window**:

- Supports any YouTube URL
- Automatically converted into an embeddable autoplay video
- Modal backdrop for cinematic feeling
- Closes with outside click or “X” button

## Advanced Search & Filtering

The application includes a filtering toolbar:

- Filter by **title**
- Filter by **genre**
- Filter by **minimum IMDb rating**
- Filter by **year range (from → to)**
- "Clear" button resets all filters
- Filters dynamically update movie results
- Marquee scrolling automatically adapts to the number of results

![Preview](./pictures/2.png)
![Preview](./pictures/3.png)
