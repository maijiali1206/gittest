package test2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


public class TechParamParser {
	
	TreeMap<String, TechParameter> words = new TreeMap<String, TechParameter>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content ="1.符合GB27897-2011《A类泡沫灭火剂》标准，并提供国家消防装备质量监督检验中心出具的完整检测报告及3C认证。"+
				"2. ★凝固点/℃≤-15℃ ，PH值:6.0-9.5。"+
				"    3、★腐蚀率: Q235A钢片：≤0.6；3A21铝片：≤0.2。"+
				"4)表面张力：≤22。"+
				"5.润湿性：混合比为1.0%时：润湿时间≤7s。"+
				"(6) 25%析液时间：温度处理前：≥10.0min 温度处理后：≥10.0min。"+
				"7.隔热防护性能：25%析液时间：≥20min    发泡倍数：≥39。"+     
				"8.灭A类火性能：灭火时间：≤75s，复燃时间：≥10min。"+   
				"9.灭非水溶性液体火性能：灭火时间≤3min,25%抗烧时间≥12min。"+
				"10.温度敏感性判定：非温度敏感性。"+
				"11.产品保质期: 2年。"+ 
				"12.塑料桶符合部消防局关于泡沫液要求，泡沫桶颜色、包装、标识符合要求，并按照要求负责卸装到指定地点。";
		
		//String content = "交换容量：19.2Tbps/48Tbps；包转发率：2880Mpps/16500Mpps；24端口千兆以太网电接口(RJ45)+4端口万兆以太网光接口，16端口万兆以太网光接口板，双电源650W，单主控；兼容40GE和100GE以太网标准，进一步融合MPLS VPN、IPv6、网络安全、无线、无源光网络等多种网络业务，提供不间断转发、不间断升级等多种高可靠技术。";
		
		TechParamParser TP = new TechParamParser();
		TP.parseContent(content);
		TP.show();
		
	}
	
	public TreeMap<String, TechParameter> getParsedResult() {
		return words;
	}
	public List<TechParameter> getParsedListResult() {
		List<TechParameter> list = new ArrayList<TechParameter>();
		Set<String> keys = words.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			list.add(words.get(it.next()));
		}
		return list;
	}
	public void parseContent(String content) {
		String o = null;
		if( null==content || content.equals("") ) return;
		
		String[] cols = content.replaceAll("：",":").replaceAll("☆", "★").replaceAll("；",";").replaceAll(";","。").split("。");
		if( cols.length>0 ) {
			for(int i=0; i<cols.length; i++) 
			{
				if( cols[i].trim().equals("") ) continue;
				o = cols[i].trim().replaceAll("^[0-9]*\\.[0-9]*","").replaceAll("^\\([0-9]*\\)", "").replaceAll("^[0-9]*\\)", "").replaceAll("^[0-9]*、", "");
				int start = -1;
				String key = "参数"+i; //默认参数名,防止没有参数名的情况
				String value = o;
				if( (start = o.indexOf(":"))>0 ) {
					key = o.substring(0, start).trim();
					value = o.substring(start+1).trim();
				}
				words.put(key, new TechParameter(key.replace("★",""), value, o.indexOf("★")>0?"Y":"N"));
			}
		}
	}
	
	public void show() {
		Set<String> keys = words.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			System.out.println(words.get(it.next()));
		}
	}
	
}
