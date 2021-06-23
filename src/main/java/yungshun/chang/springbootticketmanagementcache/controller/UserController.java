package yungshun.chang.springbootticketmanagementcache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import yungshun.chang.springbootticketmanagementcache.model.User;
import yungshun.chang.springbootticketmanagementcache.service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id, WebRequest webRequest) {

        User user = userService.getUser(id);
        long updated = user.getUpdatedDate().getTime();
        boolean isNotModified = webRequest.checkNotModified(updated);
        logger.info("{getUser} isNotModified : " + isNotModified);
        if (isNotModified) {
            logger.info("{getUser} resource not modified since last call, so exiting");
            return null;
        }
        logger.info("{getUser} resource modified since last call, so get the updated content");

        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping(value="", method=RequestMethod.POST)
    public Map<String, Object> createUser(@RequestParam(value="userid") Integer userid,
                                          @RequestParam(value="username") String username) {
        Map<String, Object> map = new LinkedHashMap<>();
        userService.createUser(userid, username);
        map.put("result", "added");
        return map;
    }

    @ResponseBody
    @RequestMapping(value="", method=RequestMethod.PUT)
    public Map<String, Object> updateUser(@RequestParam(value="userid") Integer userid,
                                          @RequestParam(value="username") String username) {
        Map<String, Object> map = new LinkedHashMap<>();
        userService.updateUser(userid, username);
        map.put("result", "updated");
        return map;
    }

    @ResponseBody
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Map<String, Object> deleteUser(@PathVariable("id") Integer userid) {
        Map<String, Object> map = new LinkedHashMap<>();
        userService.deleteUser(userid);
        map.put("result", "deleted");
        return map;
    }
}
