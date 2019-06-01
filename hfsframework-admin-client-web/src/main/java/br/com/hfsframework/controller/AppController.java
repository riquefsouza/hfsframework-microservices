package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.controller.BaseAppClientController;

@Controller
@RequestMapping("/")
//@SessionAttributes("roles")
public class AppController extends BaseAppClientController {

}
