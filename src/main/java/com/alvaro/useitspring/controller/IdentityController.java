package com.alvaro.useitspring.controller;

import com.alvaro.useitspring.models.entity.Usuarios;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@BasePathAwareController
public class IdentityController {

  @RequestMapping("/identity")
  public @ResponseBody
  PersistentEntityResource getAuthenticatedUserIdentity(
      PersistentEntityResourceAssembler resourceAssembler) {
    Usuarios user = (Usuarios) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return resourceAssembler.toResource(user);
  }
}
