


<div class="toast position-absolute top-0 end-0 p3" role="alert" aria-live="assertive" aria-atomic="true">
	<div class="toast-header" style="background-color: #004077; color: white;">
		<i class="bi bi-info-circle-fill"></i>&nbsp; <strong class="me-auto">MENSAGEM</strong>
		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
	</div>
	<div class="toast-body" style="font-weight: bold; text-transform: uppercase;">${msg}!</div>
</div>


<footer> </footer>
</div>
</div>
</div>

<script src="${contextPath}/resources/assets/js/vendors/jquery.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/perfect-scrollbar.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/dataTables.bootstrap5.min.js"></script>
<script src="${contextPath}/resources/assets/js/vendors/dataTables.buttons.min.js"></script>
<script src="${contextPath}/resources/assets/js/datatable-init.js"></script>
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

<script>
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	  return new bootstrap.Tooltip(tooltipTriggerEl)
	})
</script>

</body>

</html>