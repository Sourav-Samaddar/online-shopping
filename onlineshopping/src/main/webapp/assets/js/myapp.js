$(function() {
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'All Products':
		$('#listAllProducts').addClass('active');
		break;
	default:
		if (menu == 'Home')
			break;
		$('#listAllProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// Code for JQuery Data Table
	/*var products = [ [ 1, 'DEC' ], [ 2, 'SAT' ], [ 3, 'MON' ], [ 4, 'TUE' ],
			[ 5, 'WED' ], [ 6, 'THU' ], [ 7, 'FRI' ], [ 8, 'SUN' ]

	];*/

	var $table = $('#productListTable');

	if ($table.length) {
		//console.log('Inside the Table now');
		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}else{
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
		}
		$table.DataTable({
			lengthMenu : [[3,5,10,-1],['3 Records','5 Records','10 Records','All']],
			pageLength: 5,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [
				{
					data : 'code',
					bSortable : false,
					mRender : function(data, type, row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg" />';
					}
				},
				{
					data : 'name'
				},
				{
					data : 'brand'
				},
				{
					data : 'unitprice',
					mRender : function(data, type, row){
						return '&#8377; '+data
					}
				},
				{
					data : 'quantity',
					mRender : function(data, type, row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
					
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+window.contextRoot+ '/show/' +data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						if(row.quantity < 1){
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						else{
							str += '<a href="'+window.contextRoot+ '/cart/add' +data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						
						return str;
					}
				}
			]
		});
	}
	var $alert = $('.alert');
	
	if($alert.length){
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}
	
	//-----------Manage Product Activation and Deactivation--------------------
	
	$('.switch input[type="checkbox"]').on('change',function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked)?'You want to activate the product':
							'You want to deactivate the product';
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size:'medium',
			title:'Product Activation and Deactivation',
			message:dMsg,
			callback: function(confirmed){
				if(confirmed){
					console.log(value);
					bootbox.alert({
						size:'medium',
						title:'information',
						message:'You are going to perform operation on product'+value
					});
				}
				else{
					checkbox.prop('checked',!checked);
				}
			}
		});
	});
	
	//---------- Manage product Data Table -----------------
	
	var $table = $('#adminProductTable');

	if ($table.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		
		$table.DataTable({
			lengthMenu : [[30,50,100,-1],['30 Records','50 Records','100 Records','All']],
			pageLength: 30,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [
				{
					data : 'id'
				},
				{
					data : 'code',
					bSortable : false,
					mRender : function(data, type, row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg" />';
					}
				},
				{
					data : 'brand'
				},
				{
					data : 'name'
				},
				{
					data : 'quantity',
					mRender : function(data, type, row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
					
				},
				{
					data : 'unitprice',
					mRender : function(data, type, row){
						return '&#8377; '+data
					}
				},
				
				{
					data : 'active',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						if(data){
							str += '<label class="switch"><input type="checkbox" checked="checked" value="'+row.id+'"/><div class="slider toggle-bar-size"></div></label>';
						}
						else{
							str += '<label class="switch"><input type="checkbox" value="'+row.id+'"/><div class="slider toggle-bar-size"></div></label>';
						}
						
						return str;
					}
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						return '<a href="'+window.contextRoot+'/manage/'+row.id+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
					}
				}
			],
			
			initComplete: function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)?'You want to activate the product':
										'You want to deactivate the product';
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size:'medium',
						title:'Product Activation and Deactivation',
						message:dMsg,
						callback: function(confirmed){
							if(confirmed){
								console.log(value);
								
								var activationURL = window.contextRoot+'/manage/products/'+value+'/activation';
								$.post(activationURL,function(data){
									bootbox.alert({
										size:'medium',
										title:'information',
										message: data
									});
								});
								
							}
							else{
								checkbox.prop('checked',!checked);
							}
						}
					});
				});
			}
		});
	}
	
	//------------- Validation code for Category--------------
	
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
			
			rules : {
				name : {
					required : true,
					minlength : 2
				},
				description : {
					required : true
				}
			},
			
			messages : {
				
				name : {
					required : 'Please add the Category name!',
					minlength : 'Category Name should not be less than 2 characters!'
				},
				description : {
					required : 'Please add the Category description!'
				}
			},
			
			errorElement: 'em',
			errorPlacement: function(error,element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}
	
	//--------------------------------
});