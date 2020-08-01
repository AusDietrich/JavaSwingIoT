package mine.springGui.restCalls;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Rest {

	public void Weather() {
	 RestTemplate restTemplate = new RestTemplate();
	 ResponseEntity<String> response = restTemplate.getForEntity(
			 "http://api.openweathermap.org/data/2.5/weather?q=Tempe&appid=7c9c2ccf6a73d3623340b1923aea24e7", String.class);
	 System.out.println(response);
	}
}
