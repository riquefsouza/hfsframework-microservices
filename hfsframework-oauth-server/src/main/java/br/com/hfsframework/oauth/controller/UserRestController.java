package br.com.hfsframework.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.BaseRestController;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController extends BaseRestController<User, Long, UserService> {

}
