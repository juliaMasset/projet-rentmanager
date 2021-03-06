<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/users/create">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Nom" minlength="3" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Prenom" minlength="3" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                                    </div>
                                </div>
                                <div class="alert alert-danger" role="alert" id="expirationWarning2">
                                    Email deja pris ! 
                                </div>
                                <div class="form-group">
                                    <label for="birthdate" class="col-sm-2 control-label">Date de naissance</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="birthdate" name="birthdate" placeholder="Date de naissance" onchange="verifyAge()" required>
                                    </div>
                                </div>
                                <div class="alert alert-danger" role="alert" id="expirationWarning">
                                    Il faut avoir plus de 18 ans ! 
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right" id="add">Ajouter</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script>

document.getElementById('expirationWarning').hidden = true;

    function verifyAge(){
        var Bdate = document.getElementById('birthdate').value;
        var Bday = +new Date(Bdate);
        if(((Date.now() - Bday) / (31557600000)) > 18){
            console.log('age valide');
            document.getElementById('add').disabled = false;
            document.getElementById('expirationWarning').hidden = true;
        } else{
            console.log('age non valide');
            document.getElementById('add').disabled = true;
            document.getElementById('expirationWarning').hidden = false;
        }
    } 

    const clientsMailsList = [
                            <c:forEach var="user" items="${users}">
                                '${user.email}',                  
                            </c:forEach>                   
                            ];
    document.getElementById('expirationWarning2').hidden = true;

    $('#email').on('change',()=>{
        let result = clientsMailsList.find((element)=> element==$('#email').val());
        console.log(result)
        if(result===undefined){
            document.getElementById('add').disabled = false;
            document.getElementById('expirationWarning2').hidden = true;
        } else { //une adresse mail existe
            document.getElementById('add').disabled = true;
            document.getElementById('expirationWarning2').hidden = false;
        }
    });

</script>
</body>
</html>

