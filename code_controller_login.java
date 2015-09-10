@RequestMapping(value = "/login", method = RequestMethod.POST)
public String homeSubmit(@ModelAttribute User user, Model model, 
    HttpServletRequest request) {
	
    ...
	
    String accessToken = null, userId = null;
    accessToken = user.getAccessToken();
	
    FacebookClient facebookClient = new DefaultFacebookClient(
        accessToken, Properties.FB_APP_SECRET, 
        Version.VERSION_2_4);
    fbUser = facebookClient.fetchObject("me", 
        com.restfb.types.User.class);
    
    ...
    
    userId = fbUser.getId();
    Node userNode = GDBM.getUserNode(userId, fbUser.getName(), 
        accessToken);

    request.getSession().setAttribute(
        FacebookService.USER_ACCESS_TOKEN, accessToken);
    request.getSession().setAttribute(FacebookService.USER_ID, 
        userId);

    facebookService.processUser(userId, accessToken);
    return "redirect:";
}