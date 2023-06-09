// 获取整个页面的顶级元素
let body = document.body;
// 自定义显示的文本内容
let content = ["❤富强❤","❤民主❤","❤文明❤","❤和谐❤","❤自由❤","❤平等❤","❤公正❤","❤法治❤","❤爱国❤","❤敬业❤","❤诚信❤","❤友善❤"];
//
function Text(x, y, randContent) {
    this.x = x;
    this.y = y;
    this.randContent = randContent;
}
Text.prototype.create = function (span) {
    let body = document.body;
    span.innerHTML = content[this.randContent - 1];
    span.style.position = "absolute";
    span.style.top = this.y - 20 + 'px';
    span.style.left = this.x - 50 + 'px';
    span.style.fontWeight = "bold";
    span.style.zIndex = "1";
    span.style.userSelect = "none";
    // span.style.animation = 'remove 2s'
    body.appendChild(span);
    let i = 0, number = 1000;
    setInterval(() => {
        span.style.top = this.y - 20 - i + 'px';
        span.style.opacity = number / 1000;
        number-=8;
        i++;
    }, 10);
};
Text.prototype.out = function (span) {
    span.remove();
};
//设置随机颜色
Text.prototype.getRandomColor = function () {
    let allType = '0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f'; //16进制颜色
    let allTypeArr = allType.split(','); //通过','分割为数组
    let color = '#';
    for (let i = 0; i < 6; i++) {
        //随机生成一个0-16的数
        // let random = parseInt(Math.random() * allTypeArr.length);
        let random = Math.floor(Math.random() * (allTypeArr.length + 1));
        color += allTypeArr[random];
    }
    return color; //返回随机生成的颜色
};

export function clickHeart() {
    window.addEventListener('load',  () => {
        body.addEventListener('click', (event) => {
            // 获取当前的坐标
            let x = event.pageX;
            let y = event.pageY;
            // 获取自定义文本中任意内容
            let randContent = Math.ceil(Math.random() * content.length);
            let text = new Text(x, y, randContent);
            let span = document.createElement('span');
            span.style.color = text.getRandomColor();
            text.create(span);
            setTimeout( () => {
                text.out(span);
            }, 1900);
        })
    })
}
