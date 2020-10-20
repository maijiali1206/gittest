package test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Test4 {

	public static void main(String[] args) {
		String[] r1 = { "纳税|交税", "额度|额" };
		String[] r2 = { "资产", "额" };
		String[] r3 = { "营业", "收入|利润" };
		String[] r4 = { "从业", "人员" };
		String[] r5 = { "省级|国家级|市级", "检测报告" };
		String[] r6 = { "国有|独资|合资|合作" };
		String[] r7 = { "注册资", "本|金" };
		String[] r8 = { "注册", "址|地" };
		String[] r9 = { "制造商|代理商", "授权函|授权书|授权证明|授权文件" };
		
		
		String[] r10 = {"建设工程质量检测机构资质|建筑工程质量检测机构资质|质量检测机构资质","证书|证明|文件"};
		List<String[]> ruleList = new ArrayList<String[]>();
		ruleList.add(r1);
		ruleList.add(r2);
		ruleList.add(r3);
		ruleList.add(r4);
		ruleList.add(r5);
		ruleList.add(r6);
		ruleList.add(r7);
		ruleList.add(r8);
		ruleList.add(r9);
		ruleList.add(r10);
		String text = "（1）投标人基本资格条件：投标人具有建筑工程质量检测机构资质证书" + 
				"具有独立承担民事责任的能力；" + 
				"具有良好的商业信誉和健全的财务会计制度；" + 
				"具有履行合同所必需的设备和专业技术能力；" + 
				"有依法缴纳税收和社会保障资金的良好记录；" + 
				"参加政府采购活动前三年内，在经营活动中没有重大违法记录；" + 
				"法律、行政法规规定的其他条件；" + 
				"供应商不得为信用中国网站（www.creditchina.gov.cn）中列入失信被执行人和重大税收违法案件当事人名单的供应商，"
				+ "不得为中国政府采购网（www.ccgp.gov.cn）政府采购严重违法失信行为记录名单中被财政部门禁止参加政府采购活动的供应商（处罚决定规定的时间内）。" + 
				"法律、行政法规规定的其他条件。" + 
				"（2）投标人特定资格条件：投标人具有建设工程质量检测机构资质证书。";

		for (int i = 0; i < ruleList.size(); i++) {
			String[] rule = ruleList.get(i);
			Map<String, String> map = findKeyWordLocation(text, rule, ruleList);
			int start1 = Integer.parseInt(map.get("start1"));
			int end1 = Integer.parseInt(map.get("end1"));
			int end2 = Integer.parseInt(map.get("end2"));
			boolean flag = Boolean.parseBoolean(map.get("flag"));
			if (flag && rule.length == 1) {
				text = text.replaceAll(text.substring(start1, end1 + 1), "<b>" + text.substring(start1, end1 + 1) + "</b>");
			} else if (flag) {
				text = text.replaceAll(text.substring(start1, end2 + 1), "<b>" + text.substring(start1, end2 + 1) + "</b>");
			} 
		}
		System.out.println(text);
	}

	/**
	 * 
	 * @param text
	 * @param rule
	 * @param ruleList
	 * @return
	 */
	public static Map<String, String> findKeyWordLocation(String text, String[] rule, List<String[]> ruleList) {
		Map<String, String> map = new HashMap<String, String>();
		List<int[]> locations = new ArrayList<int[]>();
		int start1 = -100;
		int start2 = -100;
		int end1 = -100;
		int end2 = -100;
		boolean flag = true;
		String lastKeyWord = "";
		String firstKeyWord = "";
		for (int j = 0; j < rule.length; j++) {
			String[] words = rule[j].split("\\|");
			boolean flag1 = false;
			for (int h = 0; h < words.length; h++) {
				if (text.lastIndexOf(words[h]) >= 0) {
					
					int[] location= {text.lastIndexOf(words[h]),start1 + words[h].length() - 1};
					locations.add(location);
					
					if (j == 0) {
						start1 = text.lastIndexOf(words[h]);// 第一个词开头的位置
						end1 = start1 + words[h].length() - 1;// 第一个词结尾的位置
						firstKeyWord = words[h];
					} else {
						start2 = text.lastIndexOf(words[h]);// 第二个词开头的位置
						end2 = start2 + words[h].length() - 1;// 第二个词结尾的位置
						lastKeyWord = words[h];
					}
					flag1 = true;
					break;
				}
			}
			if (!flag1) {
				flag = false;
				break;
			}

			if (j > 0) {
				if (start2 - end1 > 0 && start2 - end1 <= 3) {
					flag = true;
				} else {
					StringBuffer sb = new StringBuffer(text);
					if (end1 < end2) {
						// 删除最后一个地址
						text = sb.delete(text.lastIndexOf(lastKeyWord),	text.lastIndexOf(lastKeyWord) + lastKeyWord.length()).toString();
					} else if (end1 > end2) {
						// 删除最后一个注册
						text = sb.delete(text.lastIndexOf(firstKeyWord), text.lastIndexOf(firstKeyWord) + firstKeyWord.length()).toString();
					}
					Map<String, String> res = findKeyWordLocation(text, rule, ruleList);
					start1 = Integer.parseInt(res.get("start1"));
					start2 = Integer.parseInt(res.get("start2"));
					end1 = Integer.parseInt(res.get("end1"));
					end2 = Integer.parseInt(res.get("end2"));
					flag = Boolean.parseBoolean(res.get("flag"));
				}
			}
		}
		map.put("start1", String.valueOf(start1));
		map.put("start2", String.valueOf(start2));
		map.put("end1", String.valueOf(end1));
		map.put("end2", String.valueOf(end2));
		map.put("flag", String.valueOf(flag));
		return map;
	}
}
