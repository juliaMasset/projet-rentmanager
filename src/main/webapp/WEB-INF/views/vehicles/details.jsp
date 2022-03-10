 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-3">
                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">${car.constructor} / ${car.numPlace} places</h3> 

                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Reservation(s): </b> <a class="pull-right">${fn:length(listResa)}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Client(s): </b> <a class="pull-right">${fn:length(users)}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
                            <li><a href="#users" data-toggle="tab">Clients</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Client</th>
                                            <th>Email</th>
                                            <th>Date de debut</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items="${listResa}" var="resa">
                                            <tr>
                                                <td>${resa.id}.</td>
                                                <c:forEach items="${listUsers}" var="user">
                                                    <c:if test="${resa.clientId eq user.id}">
                                                        <td>${user.firstname} ${user.lastname}</td>
                                                        <td>${user.email}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <td>
                                                    <fmt:parseDate value="${resa.dateStart}" pattern="yyyy-MM-dd" var="parsedDateTime" type="both" />
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${ parsedDateTime }" />
                                                </td>
                                                <td> <fmt:parseDate value="${resa.dateEnd}" pattern="yyyy-MM-dd" var="parsedDateTime" type="both" />
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${ parsedDateTime }" /></td>
                                                <td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="users">
                               
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th>Date de naissance</th>
                                            <th>Email</th>
                                        </tr>
                                        <c:forEach items="${users}" var="user">
                                        <tr>
                                            <td> ${user.id}.</td>
                                            <td>${user.lastname}</td>
                                            <td>${user.firstname}</td>
                                            <td>${user.birthdate}</td>
                                            <td>${user.email}</td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div> 
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>

</body>
</html>