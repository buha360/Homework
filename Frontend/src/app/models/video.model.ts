export interface Video {
  id: number;
  title: string;
  category: string | null;
  genre: string | null;
  language: string | null;
  productionCompany: string | null;
  description: string | null;
  maturityRating: string | null;
  releaseYear: number | null;
  director: string | null;
  mainCast: string | null;
  imdbRating: number | null;
  thumbnailUrl: string | null;
  introVideoUrl: string | null;
}
