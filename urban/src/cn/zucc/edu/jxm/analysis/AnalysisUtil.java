package cn.zucc.edu.jxm.analysis;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnalysisUtil {
	String ZERO = "0";
	//BigDecimal PERCENT = new BigDecimal(0.01);
	BigDecimal ONE = new BigDecimal(1);
	BigDecimal TWO = new BigDecimal(2);
	BigDecimal EIGHT = new BigDecimal(8);
	BigDecimal TEN = new BigDecimal(10);
	BigDecimal HUNDRED = new BigDecimal(100);
	BigDecimal THOUSAND = new BigDecimal(1000);
	BigDecimal FIFTY_THOUSAND = new BigDecimal(50000);
	BigDecimal ONE_HUNDRED_THOUSAND = new BigDecimal(100000);
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<Map.Entry<String, BigDecimal>> similarProject(
			ProjectCompare example, List<ProjectCompare> projects,double percent1,double percent2,double percent3,double percent4,double percent5,double percent6,double percent7,double percent8)
			throws IOException, ParseException {
		BigDecimal p1 = new BigDecimal(percent1);
		BigDecimal p2 = new BigDecimal(percent2);
		BigDecimal p3 = new BigDecimal(percent3);
		BigDecimal p4 = new BigDecimal(percent4);
		BigDecimal p5 = new BigDecimal(percent5);
		BigDecimal p6 = new BigDecimal(percent6);
		BigDecimal p7 = new BigDecimal(percent7);
		BigDecimal p8 = new BigDecimal(percent8);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		if (projects != null) {
			for (int i = 0; i < projects.size(); i++) { // 比较每一条建设信息
				BigDecimal amount = new BigDecimal(0);
				BigDecimal similar;
				ProjectCompare reference = (ProjectCompare) projects.get(i);
				similar = compareName(example.getProjectName(),
						reference.getProjectName());
				amount = amount.add(similar.multiply(p1));
				similar = compareTotalInvestment(example.getTotalInvestment(), reference.getTotalInvestment());
				amount = amount.add(similar.multiply(p2));
				similar = compareProjectPeriod(example.getProjectPeriod(),
						reference.getProjectPeriod());
				amount = amount.add(similar.multiply(p3));
				similar = compareBuildTotalArea(example.getBuildTotalArea(),
						reference.getBuildTotalArea());
				amount = amount.add(similar.multiply(p4));
				similar = compareConstructTime(example.getConstructTime(),
						reference.getConstructTime());
				amount = amount.add(similar.multiply(p5));
				similar = compareMaxSpan(example.getMaxSpan(),
						reference.getMaxSpan());
				amount = amount.add(similar.multiply(p6));
				similar = compareName(example.getGeographyAddress(),
						reference.getGeographyAddress());
				amount = amount.add(similar.multiply(p7));
				similar = compareName(example.getGeographyRegion(),
						reference.getGeographyRegion());
				amount = amount.add(similar.multiply(p8));
				amount = amount.divide(EIGHT, 3,BigDecimal.ROUND_HALF_UP);
				map.put(reference.getPid(), amount);
			}
		}

		List<Map.Entry<String, BigDecimal>> infoIds = new ArrayList<Map.Entry<String, BigDecimal>>(
				map.entrySet());

		Collections.sort(infoIds,
				new Comparator<Map.Entry<String, BigDecimal>>() {
					public int compare(Map.Entry<String, BigDecimal> o1,
							Map.Entry<String, BigDecimal> o2) {
						int x = o2.getValue().compareTo(o1.getValue());
						return (int) x; // 降序
					}
				});
		for (int i = 0; i < infoIds.size(); i++) {
			System.out.println(infoIds.get(i));
		}
		return infoIds;
	}
	
	public BigDecimal compareName(String e, String r) throws IOException { // 字符型属性的相似度
		String example = new MessageSeg().segWords(e, ","); // 分词
		String reference =new MessageSeg().segWords(r, ",");
		String[] example2 = example.split(",");
		String[] reference2 = reference.split(",");
		int count = 0;
		for (int i = 0; i < example2.length; i++) { // 统计相同词数
			for (int j = 0; j < reference2.length; j++) {
				if (example2[i].equals(reference2[j]))
					count++;
			}
		}
		BigDecimal denominator; // 分母
		if (example2.length > reference2.length)
			denominator = new BigDecimal(example2.length);
		else
			denominator = new BigDecimal(reference2.length);
		BigDecimal numerator = new BigDecimal(count); // 分子
		BigDecimal result;
		result = numerator.divide(denominator, 5,
				BigDecimal.ROUND_HALF_UP);
		return result;
	}
	
	public BigDecimal compareTotalInvestment(double example, double reference) { // 连续型数值属性的相似度[10,50000]
		BigDecimal e = new BigDecimal(Double.toString(example));
		BigDecimal r = new BigDecimal(Double.toString(reference));
		BigDecimal numerator = e.subtract(r);
		BigDecimal denominator = FIFTY_THOUSAND.subtract(TEN);
		BigDecimal result = ONE.subtract(numerator.abs().divide(denominator, 5,
				BigDecimal.ROUND_HALF_UP));
		return result;
	}
	
	public BigDecimal compareProjectPeriod(int e, int r) { // 连续型数值属性的相似度[10,1000]
		BigDecimal ee = new BigDecimal(e);
		BigDecimal rr = new BigDecimal(r);
		BigDecimal numerator = ee.subtract(rr);
		BigDecimal denominator = THOUSAND.subtract(TEN);
		BigDecimal result = ONE.subtract(numerator.abs().divide(denominator, 5,
				BigDecimal.ROUND_HALF_UP));
		return result;
	}
	
	public BigDecimal compareBuildTotalArea(double e, double r) { // 连续型数值属性的相似度[10,100000]
		BigDecimal ee = new BigDecimal(Double.toString(e));
		BigDecimal rr = new BigDecimal(Double.toString(r));
		BigDecimal numerator = ee.subtract(rr);
		BigDecimal denominator = ONE_HUNDRED_THOUSAND.subtract(TEN);
		BigDecimal result = ONE.subtract(numerator.abs().divide(denominator, 5,
				BigDecimal.ROUND_HALF_UP));
		return result;
	}
	
	public BigDecimal compareConstructTime(Date e, Date r) throws ParseException { // 有序属性的相似度[1987.1.1~2016.4.15]
		String d1 = "1987-1-1";
		String d2 = "2016-4-15";
		Date date1 = sdf.parse(d1);
		Date date2 = sdf.parse(d2);

		int day_numerator = (int) Math
				.abs(((e.getTime() - r.getTime()) / (1000 * 60 * 60 * 24)));
		int day_denominator = (int) Math.abs(((date1.getTime() - date2
				.getTime()) / (1000 * 60 * 60 * 24)));

		BigDecimal numerator = new BigDecimal(day_numerator);
		BigDecimal denominator = new BigDecimal(day_denominator);
		BigDecimal result = ONE.subtract(numerator.abs().divide(denominator, 5,
				BigDecimal.ROUND_HALF_UP));

		return result;
	}
	
	public BigDecimal compareMaxSpan(double e, double r) { // 连续型数值属性的相似度[1,100]
		BigDecimal ee = new BigDecimal(Double.toString(e));
		BigDecimal rr = new BigDecimal(Double.toString(r));
		BigDecimal numerator = ee.subtract(rr);
		BigDecimal denominator = HUNDRED.subtract(ONE);
		BigDecimal result = ONE.subtract(numerator.abs().divide(denominator, 5,
				BigDecimal.ROUND_HALF_UP));
		return result;
	}

}
