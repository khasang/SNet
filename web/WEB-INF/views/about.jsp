<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
    <div class="page-content">
        <div class="row">
            <!-- Include leff menu-->
            <jsp:include page="inc/left_menu.jsp"/>
            <!--Main Content of page -->
            <div class="col-md-10">
                <!-- Intro Content -->
                <div class="row">
                    <div class="col-md-6">
                        <img class="img-responsive"  width="750" height="450" src="${pageContext.request.contextPath}/images/logo.png" alt="">
                    </div>
                    <div class="col-md-4">
                        <h2>About SNet</h2>
                        <b><p>Snet is a simple example of social network.</p>
                        <p>We use here next technologies:
                        <ul>
                            <li>Spring Framework</li>
                            <li>Spring Security</li>
                            <li>Hibernate</li>
                            <li>PostgreSQL as database</li>
                            <li>Bootstrap template</li>
                            <li>REST</li>
                            <li>JavaScript</li>
                        </ul>
                        </p>
                        <p>You can find here :
                            <ul>
                                <li>Registration (with JQuery validation)</li>
                                <li>Custom Login form</li>
                                <li>Profile page</li>
                                <li>Chats and messages</li>
                                <li>Friends,Invites, and search friends</li>
                                <li>Workgroups and news</li>
                            </ul>
                        </p>
                        </b>
                    </div>
                </div>
                <!-- /.row -->
                <!-- Team Members -->
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="page-header">Our Team</h2>
                    </div>
                    <div class="col-md-3 text-center">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>Sergey Kuznetsov<br>
                                    <small>Team leader</small>
                                </h3>
                                <p>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 text-center">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>Konstantin Pavlov<br>
                                    <small>Java developer</small>
                                </h3>
                                <p>Realize next features:
                                        Bootstrap template,
                                        Log in form (backend + frontend),
                                        Registration form (backend + frontend),
                                        Friends (backend),
                                        Invites (backend + frontend),
                                        Search friends (backend + frontend),
                                        Workgroups CRUD(backend + frontend),
                                        Adding members to work group(backend + frontend).
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 text-center">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>Sergey Petrov<br>
                                    <small>Java developer</small>
                                </h3>
                                <p>Realize next features:
                                    User profile (backend + frontend),
                                    Friends (frontend),
                                    UserWorkgroups CRUD,
                                    My workgroups (frontend),
                                    WorkgroupsNews and UserWorkgroupsNews (backend + frontend).
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 text-center">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>Artyom Sirosh<br>
                                    <small>Java Developer</small>
                                </h3>
                                <p>Realize next features:
                                    Chats (backend + frontend),
                                    Messages (backend + frontend),
                                    Adding messages (frontend),
                                    JUnit tests,
                                    90% of custom JavaScript.
                                </p>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.row -->
            </div>
        </div>
    </div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>
