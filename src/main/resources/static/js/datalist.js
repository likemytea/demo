//隐藏下拉框div
function setDivHidden()
{
	 var addr=document.getElementById("msg")
	 addr.style.display = "none"; 
}
//展示下拉框div并填充里边的内容
function showDiv(o)
{
	 var point=new Object();
	 point.x=o.offsetLeft;
	 point.y=o.offsetTop+o.offsetHeight;
	 while(o=o.offsetParent)
	 {
	  point.x+=o.offsetLeft;
	  point.y+=o.offsetTop;
	 }
	 var addr=document.getElementById("msg")
	 addr.style.left=point.x+"px";
	 addr.style.top=point.y+"px";
	 addr.style.display = "block";
	 flushCombo(true);
}
//更新li
function updateLi(thekey,theval){
	var ele = document.createElement('li');
	var t = document.createTextNode(theval);
	ele.setAttribute("id",thekey);
	ele.className='bg';
	ele.appendChild(t);
	document.getElementById('msgul').appendChild(ele);
}

//鼠标键入事件
function flushCombo(isOnfocusEvent){
	var input = document.getElementById('myinput').value;
	if(input=='' && (!isOnfocusEvent)){
		return;
	}
	var reg =  new RegExp(input);
	//获取原始数据
	var original = getOriginalData();
	//解析为数组(如果本身就是数组，则不用解析)
	//var dataArray = JSON.parse(original);
	
	//清空层里边所有的li
	$("#msgul").find("li").remove();
	//追加默认li
	updateLi('','全部');
	//遍历数组
	for (var i = 0, l = original.length; i < l; i++) {
		var theval='';
		var thekey='';
	    for (var key in original[i]) {
	    	//找到某一条里边的name
	    	if(key=='name'){
	    		//确定这个name是否满足搜索条件
	    		if(reg.test(original[i][key])||isOnfocusEvent){
	    			theval = original[i][key];
	    		}else{
		    		continue;
		    	}
	    		
	    	}
	    	//设置name对应的业务key
	    	if(key=='strVal'){
	    		thekey=original[i][key];
	    	}
	    	//过滤掉没用的数据，因为执行到这里的还可能是数组中name前的key-value
	    	if(theval.length>0&&thekey.length>0){
	    		updateLi(thekey,theval);
	    	}
	    }
	}
}

$(document).ready(function() {
	//li点击事件
	//jquery1.7后建议用 ON $(document).on("click", "li.bg", function() {
    $("li.bg").live("click", function() {
    	//赋值隐藏域，用于传给后台
        document.getElementById("myinputChoice").value = $(this).attr('id');
        //赋值给text文本框
        document.getElementById("myinput").value = $(this).text();
        //隐藏下拉框
        setDivHidden();
    });
});
function testRes(){
	alert(document.getElementById("myinputChoice").value);
}