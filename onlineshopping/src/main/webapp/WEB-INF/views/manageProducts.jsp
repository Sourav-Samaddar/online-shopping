<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
		<c:if test="${not empty message}">
			<div class="col-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>

					${message}

				</div>
			</div>
		</c:if>
		<div class="col-md-2 offset-md-2 col-md-8">
			<div class="card bg-success">
				<div class="card-header">
					<h4>Product Management</h4>
				</div>
				<div class="card-body">
					<!-- FORM Elements -->
					<sf:form class="form-control" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group row">
							<label class="control-label col-md-4" for="name">Enter
								Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4" for="brand">Enter
								Brand Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4" for="description">Product
								Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									placeholder="Write a description" class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4" for="unitprice">Enter
								Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitprice" id="unitprice"
									placeholder="Unit Price In &#8377" class="form-control" />
								<sf:errors path="unitprice" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4" for="quantity">Quantity
								Available</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Quantity Available" class="form-control" />
							</div>
						</div>

						<!-- File element for image upload -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="file">Select
								an Image</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4" for="categoryId">Select
								Category</label>
							<div class="col-md-8">
								<sf:select class="form-control" path="categoryId"
									id="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />

								<!-- Adding Category Button -->
								<c:if test="${product.id == 0}">
									<div class="text-right">
										<br />
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-warning">Add
											Category</button>
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary" />
								<!-- Hidden Fields -->
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="active" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
							</div>
						</div>
					</sf:form>

				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-12">
			<h3>Available Product</h3>
			<hr />
		</div>
		<div class="col-12">

			<table id="adminProductTable"
				class="table table-striped table-borderd">

				<thead>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Brand</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Active</th>
						<th>Edit</th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Brand</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Active</th>
						<th>Edit</th>
					</tr>
				</tfoot>
			</table>

		</div>
	</div>
	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Add new Category</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- Category Form -->
					<sf:form modelAttribute="category" id="categoryForm"
						action="${contextRoot}/manage/category" method="POST">

						<div class="form-group row">
							<label class="control-label col-md-4" for="category_name">Category
								Name </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4" for="category_description">Category
								Description </label>
							<div class="col-md-8">
								<sf:textarea cols="" rows="5" path="description" id="category_description"
									class="form-control" />
							</div>
						</div>
						
						<div class="form-group row">
							<div class="offset-4 col-md-8">
								<input type="submit" name="submit" id="category_submit" value="Add Category"
									class="btn btn-primary" />
							</div>
						</div>
						
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>