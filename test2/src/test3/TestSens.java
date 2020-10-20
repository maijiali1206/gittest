package test3;


public class TestSens {
	static SensitiveFilter filter = SensitiveFilter.DEFAULT;
	public static void main(String[] args) {
		String sentence = "1具备行政主管部门颁发的水利工程施工监理乙级及以上具备行政主管部门颁发的水利工程施工监理乙级及以上具备行政主管部门颁发的水利工程施工监理乙级及以上具备行政主管部门颁发的水利工程施工监理乙级及以上资质或综合的资质";
		String str = filter.filter(sentence, '*');
		System.out.println(str);

	}

}
