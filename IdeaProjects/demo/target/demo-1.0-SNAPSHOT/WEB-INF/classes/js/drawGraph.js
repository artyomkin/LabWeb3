const size = 500;
document.addEventListener("DOMContentLoaded",function(){
    var canvas = document.getElementById("graph");

    var context = canvas.getContext("2d");
    context.globalCompositeOperation = 'destination-over';
    drawYAxis(context);
    drawXAxis(context);
    drawRect(context);
    drawTriangle(context);
    drawArc(context);
    context.globalCompositeOperation = 'source-over';

})

function drawYAxis(context){

    context.beginPath();

    context.moveTo(size/2, size);
    context.lineTo(size/2, 0);

    context.lineTo(size/2-5,10);

    context.moveTo(size/2,0);
    context.lineTo(size/2+5,10);

    context.moveTo(size/2-5,30);
    context.lineTo(size/2+5,30);

    context.fillText("R",size/2+15,30);

    context.moveTo(size/2-5,size-30);
    context.lineTo(size/2+5,size-30);
    context.fillText("-R",size/2+15,size-30);

    context.moveTo(size/2-5, ( size - 60 )/ 4 + 30);
    context.lineTo(size/2+5, ( size - 60 )/ 4 + 30);
    context.fillText("R/2",size/2+15,( size - 60 )/ 4 + 30);

    context.moveTo(size/2-5,( size - 60 )/ 4 * 3 + 30);
    context.lineTo(size/2+5,( size - 60 )/ 4 * 3 + 30);
    context.fillText("-R/2",size/2+15,( size - 60 )/ 4 * 3 + 30);
    context.stroke();

}

function drawXAxis(context){

    context.beginPath();
    context.moveTo(0, size/2);
    context.lineTo(size, size/2);

    context.lineTo(size-10,size/2-5);

    context.moveTo(size,size/2);
    context.lineTo(size-10,size/2+5);

    context.moveTo(size-30,size/2-5);
    context.lineTo(size-30,size/2+5);

    context.fillText("R",size-30,size/2+15);

    context.moveTo(30,size/2-5);
    context.lineTo(30,size/2+5);
    context.fillText("-R",30,size/2+15);

    context.moveTo( (size - 60) / 4 + 30 , size/2 - 5);
    context.lineTo( (size - 60) / 4 + 30 , size/2 + 5);
    context.fillText("-R/2",(size - 60) / 4 + 30, size/2+15);

    context.moveTo((size - 60) / 4 * 3 + 30,size/2-5);
    context.lineTo((size - 60) / 4 * 3 + 30,size/2+5);
    context.fillText("R/2",(size - 60) / 4 * 3 + 30,size/2+15);
    context.stroke();


}

function drawRect(context){

    context.beginPath();

    context.fillStyle = "#389583";
    context.fillRect((size - 60) / 4 + 30, 30, (size - 60) / 4,size/2 - 30);

    context.stroke();

}

function drawTriangle(context){
    context.beginPath();

    context.strokeStyle = "#389583";
    context.moveTo(size/2,(size - 60)/4 + 30);
    context.lineTo((size-60)/4*3 + 30,size/2);
    context.lineTo(size/2,size/2);
    context.closePath();
    context.fill();

    context.stroke();
}

function drawArc(context){


    context.beginPath();
    context.arc(size/2, size/2, size/2-30, Math.PI, Math.PI/2, true);
    context.lineTo(size/2,size/2);
    context.strokeStyle = '#389583';
    context.fillStyle = '#389583';
    context.fill();
    context.fillStyle = "#000";
    context.stroke();
}