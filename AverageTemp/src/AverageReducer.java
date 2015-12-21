import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.IOException;

public class AverageReducer extends Reducer <Text, IntWritable,Text, IntWritable >{
	public void reduce(Text key,  Iterable<IntWritable> values, Context context) throws IOException,InterruptedException{          
		int total_temp = 0; 
		int count = 0;
		for (IntWritable value : values){
			total_temp += value.get();     
			count+=1;
		}
		System.out.println(
				"\n########## END OF FILE ##########\n"
				+ "AVG: " + total_temp + "/" + count + " = " + total_temp/count
		);
		context.write(key, new IntWritable(total_temp/count));
	}                                             
}