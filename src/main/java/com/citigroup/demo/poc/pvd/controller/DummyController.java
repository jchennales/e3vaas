package com.citigroup.demo.poc.pvd.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('hello')")
    public Map<String, Object> hello(@RequestParam("name") Optional<String> name, Principal principal) {

    	Map<String, Object> res = new HashMap<>();
        res.put("message", "Hello " + name.orElse(principal.getName()));
        return res;
        
    }

    @RequestMapping(path = "/health", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('health')")
    public Map<String, Object> health(Principal principal) {
    	
        Map<String, Object> res = new HashMap<>();
        res.put("status", "System is up");
        return res;
        
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin')")
    public Map<String, Object> admin(Principal principal, HttpServletRequest request) {
    	
        Map<String, Object> res = new HashMap<>();
        res.put("principal", principal.getName());
        res.put("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return res;
        
    }

}
