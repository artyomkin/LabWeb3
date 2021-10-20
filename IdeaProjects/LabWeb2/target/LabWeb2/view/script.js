document.addEventListener("DOMContentLoaded", function(){
    let form = document.getElementById("coordinates_form");
    form.addEventListener("submit", formSend);

    let resultArr = new Array();
    let lastResultIndex = 0;
    const MAX_RESULTS_IN_TABLE = 3;
    const GRAPH_SIZE = 500;

    collectResults();
    checkOverflow();
    updateDotsInChart();

    function collectResults(){

        let tableRows = document.querySelectorAll(".result_row");
        for(let i = 0; i<tableRows.length; i++){

            let row = tableRows[i];
            let x = parseFloat(row.querySelector(".x_result").innerHTML.replace(',','.'));
            let y = parseFloat(row.querySelector(".y_result").innerHTML.replace(',','.'));
            let R = parseInt(row.querySelector(".R_result").innerHTML);
            let hit = row.querySelector(".hit_result").innerHTML == "true";

            let result = new Result(x,y,R,hit);

            resultArr.push(result);

        }

    }
    
    async function formSend(form_event){

        form_event.preventDefault();

        let btn = document.getElementById("submit_request");
        btn.setAttribute("disabled","disabled")

        let formReq = document.getElementsByClassName("required");

        for(let i = 0; i<formReq.length; i++){

            let inputField = formReq[i];

            inputField.classList.remove("error");

            let siblings = inputField.parentElement.childNodes;

            for (let j = 0; j<siblings.length; j++){

                if(siblings[j].className == "error_message"){
                    siblings[j].innerHTML = "";
                }

            }
            
        }

        let error = await formValidate();

        let xBoxes = document.querySelectorAll("input[type='checkbox'][name='x']");
        let RBoxes = document.querySelectorAll("input[type='checkbox'][name='R']");
        let yVal = document.querySelector('#y').value;
        let xVal = [];
        let RVal = [];
        for (let i = 0; i<xBoxes.length; i++){
            if (xBoxes[i].checked){
                xVal.push(xBoxes[i].value);
            }
        }
        for (let i = 0; i<RBoxes.length; i++){
            if (RBoxes[i].checked){
                RVal.push(RBoxes[i].value);
            }
        }

        let content = {
            x: xVal,
            y: yVal,
            R: RVal
        }
        if(!error){

            let request = new XMLHttpRequest();
            request.open('POST','controller');
            request.send(content);
            request.onreadystatechange=function(){
                if (request.readyState === XMLHttpRequest.DONE && request.status == 200){
                    console.log(request.response);
                }
            }
            // document.getElementById("coordinates_form").submit();

        }

        btn.removeAttribute("disabled");

    }
    
    async function formValidate(){
        
        let formReq = document.getElementsByClassName("required");
        
        let isEmpty = false;
        let isNumber = true;
        let isPositive = true;
        let isInRange = true;
        
        for (let i = 0; i<formReq.length; i++){
            
            let inputField = formReq[i];
                        
            let siblings = inputField.parentElement.childNodes;
            
            if(inputField.value.trim() == ""){
                
                isEmpty = true;
                
                setError(inputField, siblings, "Field must not be empty"); //TODO валидация чекбоксов, чтобы не были пустыми
                
            } else if (isNaN(inputField.value)) {
                
                isNumber = false;
                
                setError(inputField, siblings, "Value must be a number");
                
            } else if (inputField.value < 0 && inputField.id == "R"){
                
                isPositive = false;
                
                setError(inputField, siblings, "Value must be positive")
                
            } else if (inputField.id == "y" && (inputField.value<=-5 || inputField.value>=3)){
                
                isInRange = false;
                
                setError(inputField, siblings, "Value must be in range (-5;3)");
                
            }
            
        }
        
        return !(!isEmpty && isNumber && isPositive && isInRange);
        
    }
    
    function setError(inputField, siblings, error_message){
        
        inputField.classList.add("error")
                
        for (let j = 0; j<siblings.length; j++){
            
            if (siblings[j].className == "error_message"){
                siblings[j].innerHTML = error_message;
            }
            
        }
        
    }
    

    function checkOverflow(){
        
        if(resultArr.length>MAX_RESULTS_IN_TABLE){

            document.querySelector("#results").innerHTML = "";

            for (let i = 0; i<MAX_RESULTS_IN_TABLE; i++){
                document.querySelector("#results").append(createResultRow(
                    resultArr[i].x,
                    resultArr[i].y,
                    resultArr[i].R,
                    resultArr[i].hit
                ));
            }

            lastResultIndex = 2;

            let prev_btn = document.getElementById("pag_prev");
            let next_btn = document.getElementById("pag_next");
            
            prev_btn.classList.remove("hidden");
            next_btn.classList.remove("hidden");
            
            prev_btn.addEventListener("click", pagPrev);
            next_btn.addEventListener("click", pagNext);
        }
        
    }
    
    async function pagPrev(){
        
        let results = document.getElementById("results");

        if(resultArr.length>MAX_RESULTS_IN_TABLE && lastResultIndex>=MAX_RESULTS_IN_TABLE){
            
            results.removeChild(results.lastChild);
            
            let prevResult = resultArr[lastResultIndex-MAX_RESULTS_IN_TABLE];
            
            results.prepend(createResultRow(
                prevResult.x,
                prevResult.y,
                prevResult.R,
                prevResult.hit));
            
            lastResultIndex--;
            
        }
        
    }
    
    async function pagNext(){

        
        let results = document.getElementById("results");
        
        if(resultArr.length>MAX_RESULTS_IN_TABLE && lastResultIndex<resultArr.length-1){
            
            results.removeChild(results.firstChild);
            
            let nextResult = resultArr[lastResultIndex+1];
            
            results.append(createResultRow(
                nextResult.x,
                nextResult.y,
                nextResult.R,
                nextResult.hit
            ));
            
            lastResultIndex++;
        }
             

        
    }

    function createResultRow(x,y,R,hit){
        let resultRow = document.createElement("tr");
        let xTag = document.createElement("td");
        xTag.classList.add("left");
        xTag.classList.add("x_result");
        xTag.innerHTML = x.toString();

        let yTag = document.createElement("td");
        yTag.classList.add("y_result");
        yTag.innerHTML = y.toString();

        let RTag = document.createElement("td");
        RTag.classList.add("R_result");
        RTag.innerHTML = R.toString();

        let hitTag = document.createElement("td");
        hitTag.classList.add("hit_result");
        hitTag.classList.add("right")
        hitTag.innerHTML = hit;

        resultRow.append(xTag);
        resultRow.append(yTag);
        resultRow.append(RTag);
        resultRow.append(hitTag);

        return resultRow;
    }
    
    function updateDotsInChart(){

        for (let i = 0; i<resultArr.length; i++){
            let result = resultArr[i];

            let x = Number(result.x);
            let y = Number(result.y);
            let R = Number(result.R);
            let hit = result.hit;

            let xPercentage = calculatePercentage(x,R);
            let yPercentage = calculatePercentage(-y,R);

            if(R<=0 && x==0 && y == 0){
                xPercentage = 50;
                yPercentage = 50;
            }

            drawDot(xPercentage, yPercentage, hit);

        }

        
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

    function drawDot(xPercentage, yPercentage, green){

        let canvas = document.getElementById("graph");
        let context = canvas.getContext("2d");

        context.beginPath();

        context.fillStyle = green ? "green" : "red";

        context.arc(
            xPercentage * GRAPH_SIZE / 100,
            yPercentage * GRAPH_SIZE / 100,
            5,
            0,
            Math.PI*2,
            false
        )

        context.fill();

        context.fillStyle = "black";

        context.stroke();

    }

})