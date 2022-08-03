package quiz07_song;

public class Song {
	
	private String title;
	private double playtime;
	
	public Song(String title, double playtime) {
		super();
		this.title = title;
		this.playtime = playtime;
	}

	@Override
	public String toString() {
		return "Song [title=" + title + ", playtime=" + playtime + "]";
	}
	
	
}
