/**
 * 
 */
package cz.wicketstuff.examples.spring.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Martin Strejc (strma17)
 *
 */
@RestController
public class HelthCheck {
	 
	private final AtomicLong counter = new AtomicLong();
	
	private static final String CHECK_TEMPLATE = "Responded message: %s, counter: %d";
	
	@RequestMapping("/check/${message}")
	public String check(@RequestParam(value="message", defaultValue="", required = false) String inMessage) {
		return String.format(CHECK_TEMPLATE, inMessage, counter.incrementAndGet());
	} 
	
}
