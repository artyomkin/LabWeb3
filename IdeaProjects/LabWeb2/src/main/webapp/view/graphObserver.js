document.addEventListener("DOMContentLoaded", function(){

    let graph = document.getElementById("graph");
    let boundingClientRect = graph.getBoundingClientRect();

    let xLeft = boundingClientRect.left;
    let yTop = boundingClientRect.top

    let width = graph.offsetWidth;
    let height = graph.offsetHeight;
    let R;
    const GRAPH_SIZE = 500;

    graph.addEventListener("click", calculateHit);

    function calculateHit(event){

        event.preventDefault();

        if(getRadius().length === 1){
            document.querySelector('.radius_error').setAttribute("style","display:none");
            let x = event.pageX - width;
            let y = yTop - event.pageY + width/2;
            sendForm(x,y,getRadius()[0]);

        } else if (getRadius().length > 1){
            setRaidusError("You should select only one radius");
        } else {
            setRaidusError("You should select at least one radius");
        }


    }
    function setRaidusError(message){
        let radiusErrorTag = document.querySelector('.radius_error');
        radiusErrorTag.setAttribute("style","display: visible;");
        radiusErrorTag.innerHTML = message;
    }
    function sendForm(x,y,R){
        x = x/(GRAPH_SIZE/2) * R;
        y = y/(GRAPH_SIZE/2) * R;
        let content = "x=" + x + "&y=" + y + "&R=" + R + "&async=true";
        let request = new XMLHttpRequest();

        request.open('POST','controller');
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        request.send(content);
        request.onreadystatechange=function(){
            if (request.readyState === XMLHttpRequest.DONE && request.status == 200){
                let response = JSON.parse(request.response);
                let dots = response.dots;
                let workingTime = response.workingTime;
                let currentTime = response.currentTime;
                for (let i = 0; i<dots.length; i++){
                    console.log(calculatePercentage(dots[i].x, dots[i].R));
                    console.log(calculatePercentage(dots[i].y, dots[i].R));
                    drawDot(
                        calculatePercentage(dots[i].x, dots[i].R),
                        calculatePercentage(-dots[i].y, dots[i].R),
                        dots[i].hit
                    );
                }
                setWorkingTime(workingTime);
                setCurrentTime(currentTime);
            }
        }
    }

    function getRadius(){
         let checkboxes = document.querySelectorAll("input[type='checkbox'][name='R']");
         let result = [];
         for (let i = 0; i<checkboxes.length; i++){
             if (checkboxes[i].checked) {
                 result.push(parseInt(checkboxes[i].value));
             }
         }
         return result;
    }


    function calculatePercentage(n, R){

        let percentage = 50 + n/R * 100 * 0.5;

        if(percentage > 90) {
            percentage = 90
        } else if (percentage < 6){
            percentage = 6
        }
        return percentage;
    }

    function setWorkingTime(workingTime){
        let workingTimeTag = document.querySelector('#working_time');
        workingTimeTag.innerHTML = workingTime;
    }

    function setCurrentTime(currentTime){
        let currentTimeTag = document.querySelector('#current_time');
        currentTimeTag.innerHTML = currentTime;
    }

})