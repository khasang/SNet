<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-2">
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <!-- Main menu -->
            <li class="current"><a href="/profile"><i class="glyphicon glyphicon-eye-open"></i> Profile</a></li>
            <li><a href="/chat"><i class="glyphicon glyphicon-envelope"></i> Chat</a></li>

            <li class="submenu">
                <a href="#">
                    <i class="glyphicon glyphicon-user"></i> Friends
                    <span class="caret pull-right"></span>
                </a>
                <!-- Sub menu -->
                <ul>
                    <li><a href="/friends"><i class="glyphicon glyphicon-user"></i> My Friends</a></li>
                    <li><a href="/invites"><i class="glyphicon glyphicon-plus-sign"></i> Invites</a></li>
                    <li><a href="/searchFriends"><i class="glyphicon glyphicon-search"></i> Search Friends</a></li>
                </ul>
            </li>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN')" var="isUSer"/>
            <li class="submenu">
                <a href="#">
                    <i class="glyphicon glyphicon-briefcase"></i> Workgroups
                    <span class="caret pull-right"></span>
                </a>
                <!-- Sub menu -->
                <ul>
                    <li><a href=""><i class="glyphicon glyphicon-inbox"></i>  My workgroups</a></li>
                    <li><a href=""><i class="glyphicon glyphicon-send"></i>  My News</a></li>
                    <c:if test="${isUSer}">
                    <li><a href="/allWorkgroups"><i class="glyphicon glyphicon-th-list"></i>  Manage workgroups</a></li>
                    </c:if>
                </ul>
            </li>

            <li class="submenu">
                <a href="#">
                    <i class="glyphicon glyphicon-list"></i> Other Pages
                    <span class="caret pull-right"></span>
                </a>
                <!-- Sub menu -->
                <ul>
                    <li><a href="/login">Log In</a></li>
                    <li><a href="/register">Register</a></li>
                </ul>
            </li>
            <c:if test="${isUSer}">
            <li class="submenu">
                <a href="#">
                    <i class="glyphicon glyphicon-folder-close"></i> Bootstrap Pages
                    <span class="caret pull-right"></span>
                </a>
                <!-- Sub menu -->
                <ul>
                    <li><a href="/tables"><i class="glyphicon glyphicon-list"></i> Tables</a></li>
                    <li><a href="/buttons"><i class="glyphicon glyphicon-record"></i> Buttons</a></li>
                    <li><a href="/forms"><i class="glyphicon glyphicon-tasks"></i> Forms</a></li>
                </ul>
            </li>
            </c:if>
            <li><a href="/about"><i class="glyphicon glyphicon-info-sign"></i> About</a></li>
        </ul>
    </div>
</div>