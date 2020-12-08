package ca.jrvs.apps.trading.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class AppController {

  @ApiOperation(value = "Show health", notes = "Show health of the app")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/health")
  public String getHealth() {
    return "I'm healthy and busy! V2 \n";
  }

}
