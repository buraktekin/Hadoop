import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.IOException;

public class AverageTemp extends Mapper <LongWritable, Text, Text, IntWritable>{
	public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
		String line = value.toString();
		if(line.matches("(.*)2002(.*)")){
			int i = line.indexOf("2002");
			line = line.substring(i, i+14);
			System.out.println("Pattern Found: " + line);
			String year = line.substring(0,4);
			String month = line.substring(4,6);
			String day = line.substring(6,8);
			String hour = line.substring(8,10);
			String minute = line.substring(10,12);
			int temperature = Integer.parseInt(line.substring(12, 14));
			System.out.println(
					"Year: " + year + "\n" +
					"Month: " + month + "\n" +
					"Day: " + day + "\n" +
					"Hour: " + hour + "\n" +
					"Minute: " + minute + "\n" +
					"Temperature: " + temperature + "\n" +
					"---------------------------------------"
			);
			context.write(new Text(year),new IntWritable(temperature));
		}
		else{
			System.out.println("No matching pattern in this line.\n "
					+ "---------------------------------------");
		}
	}
}