public class Movie implements Comparable<Movie>{

    private int id;
    private int likes;
    private String title;

    public Movie(int id, int likes, String title) {
        this.id = id;
        this.likes = likes;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Movie x) {
        if(this.getLikes()==x.getLikes())
            return (this.getTitle()).compareTo(x.getTitle());
        else
        if(this.getLikes()<x.getLikes())
            return 1;
        else
            return -1;
    }

}