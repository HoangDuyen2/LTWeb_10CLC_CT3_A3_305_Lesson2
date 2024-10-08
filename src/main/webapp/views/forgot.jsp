<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/20/2024
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- BEGIN CONTENT -->
<div class="col-md-9 col-sm-9">
  <h1>Forgot Your Password?</h1>
  <div class="content-form-page">
    <div class="row">
      <div class="col-md-7 col-sm-7">
        <form class="form-horizontal form-without-legend" action="${pageContext.request.contextPath}/forgot-password" method="post" role="form">
          <c:if test="${alertMsg !=null}">
            <h3 class="alert alert-danger">${alertMsg}</h3>
          </c:if>
          <div class="form-group">
            <label for="username" class="col-lg-4 control-label">Username</label>
            <div class="col-lg-8">
              <input type="text" class="form-control" id="username" name="username">
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-lg-4 control-label">Password <span class="require">*</span></label>
            <div class="col-lg-8">
              <input type="text" class="form-control" id="password" name="password">
            </div>
          </div>
          <div class="form-group">
            <label for="password-repeat" class="col-lg-4 control-label">Re-Password <span class="require">*</span></label>
            <div class="col-lg-8">
              <input type="text" class="form-control" id="password-repeat" name="password-repeat">
            </div>
          </div>
          <div class="row">
            <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-5">
              <button type="submit" class="btn btn-primary">Reset password</button>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-10 padding-right-30">
              <hr>
              <div class="login-socio">
                <ul class="social-icons">
                  <h5>New here? <a href="${pageContext.request.contextPath}/register">Sign Up.</a></h5>
                  <h5>Already have an account? <a href="${pageContext.request.contextPath}/login">Sign In.</a></h5>
                </ul>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- END CONTENT -->