export interface ApiResponse {
    city: {
      cordinates: string;
      playlist: string;
      name: string;
      temperature: string;
    };
    songs: {
      artista: string;
      name: string;
      albun: string;
      URL: string;
    }[];
  }
  