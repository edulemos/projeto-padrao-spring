</div>

<div class="toast position-absolute top-0 start-0" role="alert" aria-live="assertive" aria-atomic="true">
	<div class="toast-header" style="background-color: #004077; color: white;">
		<i class="bi bi-info-circle-fill"></i>&nbsp; <strong class="me-auto">MENSAGEM</strong>
		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
	</div>
	<div class="toast-body" style="font-weight: bold; text-transform: uppercase;">${msg}!</div>
</div>


<script src="${contextPath}/resources/assets/js/vendors/jquery.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/jquery.inputmask.min.js"
<script src="${contextPath}/resources/assets/js/main.js"></script>


<c:if test="${msg != null}">
	<script>
	   var toastElList = [].slice.call(document.querySelectorAll('.toast'))
       var toastList = toastElList.map(function(toastEl) {
         return new bootstrap.Toast(toastEl) 
       });
      toastList.forEach(toast => toast.show()); 
	</script>
</c:if>


</body>

</html>