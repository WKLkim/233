window.onload = function () {
    //需求1：鼠标放到哪个li上面，span对应移动到该li上。移开后，回到原位置。
    //需求2：鼠标点击那个li记录该li标签，移开的时候span回到该记录的li标签上。
    //步骤：
    //1.老三步
    //2.计数器

    //需求1：鼠标放到哪个li上面，span对应移动到该li上。移开后，回到原位置。
    //1.老三步
    let li = document.getElementsByClassName('nav-list');
    let liWidth = li[0].offsetWidth;
    let span = document.getElementById('move');
    //计数器
    let count = 0;

    //for循环绑定事件
    for(let i=0;i<li.length;i++){
        //自定义属性，然后绑定index属性为索引值
        li[i].index = i;
        //鼠标进入事件
        li[i].onmouseover = function () {
            //让span运动到该li的索引值位置
            //图片运动需要封装的方法
            animate(span,this.index*liWidth);
        }
        //鼠标移开
        li[i].onmouseout = function () {
            //让span运动到该li的索引值位置
            //图片运动需要封装的方法
            animate(span,count*liWidth);
        }
        //点击事件，记录功能
        li[i].onclick = function () {
            //需要一个计数器，每次点击以后把所以只记录下来
            //因为onmouseout事件要用到这个计数器，所以应该是一个全局变量
            count = this.index;
            animate(span,count*liWidth);
        }

    }

    //缓动动画封装
    function animate(ele,target) {
        clearInterval(ele.timer);
        ele.timer = setInterval(function () {
            let step = (target-ele.offsetLeft)/10;
            step = step>0?Math.ceil(step):Math.floor(step);
            ele.style.left = ele.offsetLeft + step + "px";
            console.log(22);
            if(Math.abs(target-ele.offsetLeft)<Math.abs(step)){
                ele.style.left = target + "px";
                clearInterval(ele.timer);
            }
        },18);
    }

    let arr = [
        {   //  1
            width:100,
            top:120,
            left:0,
            opacity:20,
            zIndex:2
        },
        {  // 2
            width:200,
            top:170,
            left:-50,
            opacity:80,
            zIndex:3
        },
        {   // 3
            width:300,
            top:150,
            left:150,
            opacity:100,
            zIndex:4
        },
        {  // 4
            width:200,
            top:170,
            left:450,
            opacity:80,
            zIndex:3
        },
        {   //5
            width:100,
            top:120,
            left:500,
            opacity:20,
            zIndex:2
        },
        {   //6
            width:80,
            top:100,
            left:250,
            opacity:20,
            zIndex:1
        }
    ];

    //0.获取元素
    let slide = document.getElementById("slide");
    let liArr = slide.getElementsByClassName('img-list');
    let arrow = slide.children[1];
    let arrowChildren = arrow.children;
    //设置一个开闭原则变量，点击以后修改这个值。
    let flag = true;

    //1.鼠标放到轮播图上，两侧的按钮显示，移开隐藏。
    slide.onmouseenter = function () {
        //arrow.style.opacity = 1;
        animateImg(arrow,{"opacity":100});
    }
    slide.onmouseleave = function () {
        //arrow.style.opacity = 1;
        animateImg(arrow,{"opacity":0});
    }

    move();
    //3.把两侧按钮绑定事件。(调用同一个方法，只有一个参数，true为正向旋转，false为反向旋转)
    arrowChildren[0].onclick = function () {
        if(flag){
            flag = false;
            move(true);
        }
    }
    arrowChildren[1].onclick = function () {
        if(flag){
            flag = false;
            move(false);
        }
    }

    //4.书写函数。
    function move(bool){
        //判断：如果等于undefined,那么就不执行这两个if语句
        if(bool === true || bool === false){
            if(bool){
                arr.unshift(arr.pop());
            }else{
                arr.push(arr.shift());
            }
        }
        //在次为页面上的所有li赋值属性，利用缓动框架
        for(let i=0;i<liArr.length;i++){
            animateImg(liArr[i],arr[i], function () {
                flag = true;
            });
        }
    }
    var img = document.getElementById("jd");
    //2.绑定事件(悬停事件：鼠标进入到事件源中立即出发事件)
    img.onmouseover = function () {
        //3.书写事件驱动程序(修改src)
        img.src = "../images/yasuo2.jfif";
//                this.src = "image/jd2.png";
    }


    //1.获取事件源
    var img = document.getElementById("jd");
    //2.绑定事件(悬停事件：鼠标进入到事件源中立即出发事件)
    img.onmouseout = function () {
        //3.书写事件驱动程序(修改src)
        this.src = "../images/yasuo.jfif";
    }



}
