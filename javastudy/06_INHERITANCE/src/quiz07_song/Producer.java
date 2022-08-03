package quiz07_song;

public class Producer {
	
	private String name;
	private Singer singer;
	private Song song;

	public Producer(String name) {
		super();
		this.name = name;
	}
	

	public void produce(Singer singer, Song song) {
		singer.addSong(song);
	}
	
}
