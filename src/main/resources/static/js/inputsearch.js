
var flag1 = 0;
var flag2 = 0;
var pos=-1, pos2=-1;
 
function init(){	
	var t, t2;
	$("#listEmail").css("top",$("#inputBox").css("top")+$("#inputBox").css("height"));
	$("#listEmail").css("left",$("#inputBox").css("left"));
	$("#listName").css("top",$("#inputBox").css("top")+$("#inputBox").css("height"));
	$("#listName").css("left",$("#inputBox").css("left"));
	hide();
	$("li").css("font-size",$("#s_custemail").css("font-size"));
	$("li").css("font-size",$("#s_custname").css("font-size"));
	$("li").css("text-align","left");
	
	bindInput();
 
	var email = $("#s_custemail");
	var name = $("#s_custname");
 
	$("#listEmail li").mouseup(function(e){
		if (t) {
			clearInterval(t);
		};
		var str = $(this).text();
		setValue(email, str);
	});
 
	$("#listName li").mouseup(function(){
		if (t2) {
			clearInterval(t2);
		};
		var str = $(this).text();
		setValue(name, str);
	}); 
	
	$('#listEmail li').mousemove(function(e){
		$flag1 = 1;		
	});
 
	$('#listName li').mousemove(function(e){
		$flag2 = 1;		
	});
	
	$("#s_custemail").focus(function(){
		$flag1 = 0;
	});
 
	$("#s_custname").focus(function(){
		$flag2 = 0;
	});
 
	$("#s_custemail").blur(function(){
		if (!$flag1) {
			hide();
		} else {
			t = setTimeout("hide()",300);
		}
	});
 
	$("#s_custname").blur(function(){
		if (!$flag2) {
			hide();
		} else {
			t2 = setTimeout("hide()",300);
		}
	});	
}
 
 
function bindInput(){
	//IE
	if (window.ActiveXObject){
		document.getElementById('s_custemail').attachEvent("onpropertychange",show1);
		document.getElementById('s_custname').attachEvent("onpropertychange",show2);
	}else{
		$("#s_custemail").bind("input",show1);
		$("#s_custname").bind("input",show2);
	}
}
 
function show1(){
	var s_value = $("#s_custemail").val();
	$("#listEmail").children().children("li").hide();
	showListEmail();
	if(s_value.length >= 1){
		$("#listEmail").children().children("li").each(function(){
			var tmp = $(this).text().substr(0,$(this).text().length);
			
			if(tmp && s_value==tmp.substr(0,s_value.length)){
				$(this).show();
			}
		});
	}
}
 
function show2(){
	var s_value = $("#s_custname").val();
	$("#listName").children().children("li").hide();
	showListName();
	if(s_value.length >= 1){
		$("#listName").children().children("li").each(function(){
			var tmp = $(this).text().substr(0,$(this).text().length);
			
			if(tmp && s_value==tmp.substr(0,s_value.length)){
				$(this).show();
			}
		});
	}
}
 
function hide(){
	$("#listEmail").css("display","none");
	$("#listName").css("display","none");
}
 
function setValue(obj, str){
	obj.val(str);
	hide();
	obj.focus();
}
 
function showListEmail(){
    var obj = $('#s_custemail');
    $("#listEmail").css("left",parseInt(obj.offset().left));
    $("#listEmail").css("top",parseInt(obj.offset().top +obj.outerHeight()));    
    $("#listEmail").css('display', 'block');
}
 
function showListName(){
    var obj = $('#s_custname');
    $("#listName").css("left",parseInt(obj.offset().left));
    $("#listName").css("top", parseInt(obj.offset().top + obj.outerHeight()));    
    $("#listName").css('display', 'block');
}