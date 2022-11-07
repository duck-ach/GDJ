package batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class FreeJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		File file = new File("C:\\GDJ", "top.txt");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file, StandardCharsets.UTF_8);
			bw = new BufferedWriter(fw);
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
