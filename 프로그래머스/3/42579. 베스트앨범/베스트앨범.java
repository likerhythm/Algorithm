import java.util.*;

class Solution {
    static class Album implements Comparable<Album>{
        int index;
        int play;
        
        public Album(int index, int play) {
            this.index = index;
            this.play = play;
        }
        
        @Override
        public int compareTo(Album album) {
            if (play == album.play) {
                return index - album.index;
            }
            return album.play - play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreRank = new HashMap<>();
        Map<String, List<Album>> albumRank = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            genreRank.put(genre, genreRank.getOrDefault(genre, 0) + play);
            
            albumRank.putIfAbsent(genre, new ArrayList<>());
            albumRank.get(genre).add(new Album(i, play));
        }
        
        List<String> sortedGenres = new ArrayList<>(genreRank.keySet());
        sortedGenres.sort((a, b) -> genreRank.get(b) - genreRank.get(a));
        
        List<Integer> answerList = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Album> albums = albumRank.get(genre);
            Collections.sort(albums);
            for (int i=0; i<Math.min(2, albums.size()); i++) {
                answerList.add(albums.get(i).index);
            }
        }
        
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}