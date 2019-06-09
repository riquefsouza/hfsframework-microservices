package br.com.hfsframework.util.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	//private static final Logger log = LoggerFactory.getLogger(ResourceUtil.class);

	public static String[] resourceHandler() { 
		//List<String> resources = Arrays.asList("/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**");
		//String[] res = resources.stream().toArray(String[]::new);
		
		String[] res = { "/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**" };
		return res;
	}
	
	public static String[] resourceLocations() {
		//List<String> resources = Arrays.asList("/WEB-INF/static/css/","/WEB-INF/static/img/","/WEB-INF/static/js/",
			//"/WEB-INF/static/primeui/", "/WEB-INF/static/scss/", "/WEB-INF/static/vendor/");
		//String[] res = resources.stream().toArray(String[]::new);
		
		String[] res = { "/WEB-INF/static/css/","/WEB-INF/static/img/","/WEB-INF/static/js/",
				"/WEB-INF/static/primeui/", "/WEB-INF/static/scss/", "/WEB-INF/static/vendor/" };
		
		return res;
	}
	
	public static String[] resourceSwagger() {
		String[] res = { "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/configuration/**" };
		return res;
	}
	
	public static boolean findResourceUrl(String[] resources, String url) {
		boolean ret = false;
		
		ArrayList<String> resourcesList = new ArrayList<String>(Arrays.asList(resources));
		resourcesList.add("/public/**");
		String[] permitAll = resourcesList.stream().toArray(String[]::new);		
		
		for (String item : permitAll) {
			item = item.replaceAll("[**]", "");

			String pat = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+"+item+"+[-a-zA-Z0-9+&@#/%=~_|]";
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
