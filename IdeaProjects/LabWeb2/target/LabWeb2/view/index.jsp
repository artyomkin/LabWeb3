<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <title>Артемкин Артем P3211</title>
</head>

<header>
    <div class="content">
        <div class="header_wrapper">
            <div class="left">
                <span class="name">Артемкин Артем</span>
                <span class="group">P3211</span>
                <span class="variant">Вариант 12001</span>
            </div>
            <div class="right">
                <span>Текущее время -
                    <span id='current_time'>
                    </span>
                </span>

                <span>Время работы -
                    <span id='working_time'>
                    </span> мкс
                </span>
            </div>
        </div>
    </div>

</header>

<body>
<div class="content">
    <div class="content_wrapper">
        <div class="left">
            <canvas id="graph" width="500px" height="500px">
            </canvas>
        </div>
        <div class="right">
            <div class="coordinates">
                <form method="post" action="controller" id="coordinates_form">
                    <div class="field">

                        <div>Изменение x</div>
                        <input type="hidden" id="hidden_x">
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="-2">-2</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="-1.5">-1.5</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="-1">-1</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="-0.5">-0.5</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="0">0</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="0.5">0.5</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="1">1</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="1.5">1.5</p>
                        <p class="radio_btns radio_x"><input type="checkbox" name="x" value="2">2</p>

                        <span class="error_message"></span>
                    </div>

                    <div class="field">
                        <br>
                        <label for="y">Введите y</label>
                        <input type="hidden" id="hidden_y">
                        <input type="text" name="y" id="y" class="required number" maxlength="10">
                        <span class="error_message"></span>
                    </div>

                    <div class="field">
                        <br>
                        <div>Изменение R</div>
                        <p class="radio_btns radio_R"><input type="checkbox" name="R" value="1">1</p>
                        <p class="radio_btns radio_R"><input type="checkbox" name="R" value="2">2</p>
                        <p class="radio_btns radio_R"><input type="checkbox" name="R" value="3">3</p>
                        <p class="radio_btns radio_R"><input type="checkbox" name="R" value="4">4</p>
                        <p class="radio_btns radio_R"><input type="checkbox" name="R" value="5">5</p>
                        <span class="error_message"></span>
                    </div>


                    <br>
                    <input type="submit" value="Отправить" id="submit_request">
                </form>
            </div>
        </div>
    </div>
    <div class="table">
        <table>
            <thead>
            <td class="top left">x</td>
            <td class="top">y</td>
            <td class="top">R</td>
            <td class="top right">Попадание</td>
            </thead>

            <tbody id="results">
                <c:forEach var="result" items="${applicationScope.get('dots')}">
                    <tr class="result_row">
                        <td class="left x_result"><fmt:formatNumber type="number" value="${result.getX()}" maxFractionDigits="2"></fmt:formatNumber></td>
                        <td class="y_result"><fmt:formatNumber type="number" value="${result.getY()}" maxFractionDigits="2"></fmt:formatNumber></td>
                        <td class="R_result"><c:out value="${result.getR()}"></c:out></td>
                        <td class="right hit_result"><c:out value="${result.isHit()}"></c:out></td>
                    </tr>
                </c:forEach>
            </tbody>

            <tfoot>
            <td class="left bot"></td>
            <td class="bot"></td>
            <td class="bot"></td>
            <td class="right bot"></td>
            </tfoot>
        </table>

        <div class="pagination">
            <span id="pag_prev" class="hidden">Previous</span>
            <span id="pag_next" class="hidden">Next</span>
        </div>
    </div>

</div>

</body>

<style>

    @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');

    *{
        padding: 0;
        margin: 0;
        font-family: 'Nanum Gothic', sans-serif;
    }

    .content{
        max-width: 1000px;
        margin: auto;
    }

    header{
        background-color: #389583;
        padding: 20px 50px 20px 50px;
        font-weight: 600;
    }

    .header_wrapper{
        display: flex;
        justify-content: space-between;
    }

    #graph{
        height: 500px;
    }
    .header_wrapper span{
        margin: 0 10px 0 10px;
    }


    body{
        width: 1500px;
        background-color: #5cdb94;
        padding-bottom: 100px;
    }

    body .content_wrapper{
        display: flex;
        justify-content: center;
        padding-top: 100px;
    }

    body .content_wrapper .left, body .content_wrapper .right{
        width: 500px;
        display: flex;
        justify-content: center;
    }

    .chart{
        width: 350px;
        height: 350px;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }

    .chart .axises{
        width: 350px;
        height: 350px;
        display: flex;
        justify-content: center;
        position: relative;
    }

    .chart .axis{
        position: relative;
    }

    .chart .line{
        width: 2px;
        height: 100%;
        background-color: #415150;
        position: absolute;
    }

    .chart .arrow{
        width: 10px;
        height: 10px;
        border-top: 2px solid black;
        border-left: 2px solid black;
        transform: rotate(45deg);
        position: absolute;
        margin-left: -5px;
    }

    .chart .x_axis{
        transform: rotate(90deg);
    }

    .chart .marks_wrapper{
        position: absolute;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .chart .outer_marks{
        height: 80%;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .chart .inner_marks{
        height: 50%;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .chart .coordinate{
        width: 30px;
        font-size: 13px;
        margin-left: 5px;
    }

    .chart .x_axis .coordinate{
        transform: rotate(-90deg);
    }

    .chart .serif{
        width: 10px;
        height: 2px;
        background-color: #415150;
        margin-left: -4px;
    }

    .chart .mark{
        display: flex;
    }

    .chart .top_mark{
        align-items: flex-start;
    }

    .chart .bot_mark{
        align-items: flex-end;
    }

    .chart .circle{
        width: 40%;
        height: 40%;
        background-color: #389583;
        margin-left: -40%;
        margin-bottom: -40%;
        position: absolute;
        z-index: -1;
        transform: rotate(270deg);
        border-radius: 100% 0 0 0;
    }

    .chart .triangle{
        width: 20%;
        height: 20%;
        margin-right: -20%;
        margin-top: -20%;
        background-color: #389583;
        position: absolute;
        z-index: -1;
        transform: rotate(180deg);
    }

    .chart .triangle::after{
        content: "";
        position: absolute;
        border: 42px solid #5cdb94;
        border-top: 42px solid transparent;
        border-right: 42px solid transparent;
    }

    .chart .rectangle{
        width: 20%;
        height: 40%;
        background-color: #389583;
        position: absolute;
        z-index: -1;
        transform: translate(-50%,-50%);
    }

    .content_wrapper .right{
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
    }

    .content_wrapper .right form{
        display: flex;
        flex-direction: column;
    }

    .content_wrapper .coordinates{
        width: 80%;
        margin: auto;
    }

    .content_wrapper .right input[type="text"]{
        max-width: 300px;
        border: none;
        border-radius: 10px;
        margin-top: 20px;
        height: 35px;
        padding-left: 15px;
    }

    .content_wrapper .right input[type="text"]:focus{
        border: none;
        outline: none;
    }

    .content_wrapper .right input[type="submit"]{
        width: 100px;
        border: none;
        border-radius: 10px;
        height: 50px;
        width: 150px;
        margin-top: 20px;
        background-color: #05396b;
        color: #e3f9f5;
        font-weight: bold;
        transition: .2s;
    }

    .content_wrapper .right input[type="submit"]:hover{
        background-color: #00999f;
        cursor: pointer;
        transition: .2s;
    }

    .content_wrapper .right input[type="submit"]:active{
        background-color: #e3f9f5;
        cursor: pointer;
        color:#40514e;
        transition: .2s;
    }

    .content_wrapper .field{
        display: flex;
        flex-direction: column;
    }

    .table{
        max-width: 350px;
        min-width: 200px;
        margin-left: 75px;
        margin-top: 50px;
    }

    table{
        border-collapse: collapse;
        width: 100%;
    }

    table td{
        padding: 15px;
        text-align: center;
        border: 1px solid #40514e;
    }

    table .top{
        border-top:none;
    }

    table .bot{
        border-bottom: none;
    }

    table .left{
        border-left: none;
    }

    table .right{
        border-right: none;
    }

    .error{
        box-shadow: 0 0 30px #40514e;
    }

    .error_message{
        margin-top: 10px;
        margin-left: 10px;
        font-size: 13px;
        height: 10px;
    }

    .table .pagination{
        width: 80%;
        margin: auto;
        margin-top: 20px;
        display:  flex;
        justify-content: space-between;
        font-size: 13px;
    }

    .table .pagination span{
        transition: .2s;
    }

    .hidden{
        display: none;
    }

    .table .pagination span:hover{
        cursor: pointer;
        color: #e4f8f6;
        transition: .2s;
    }

    #dots{
        position: absolute;
        width: 80%;
        height: 80%;
    }

    #dots .dot{
        width: 6px;
        height: 6px;
        border-radius: 100%;
        position: absolute;
        transform: translate(-50%, -50%);
    }

    #dots .green_dot{
        background-color: green;
    }

    #dots .red_dot{
        background-color: red;
    }

    .radio_btns{
        display: inline;
    }

</style>

<script type="text/javascript">
    <%@ include file="Result.js"%>
</script>
<script type="text/javascript" charset="UTF-8">
    <%@ include file="graph.js" %>
    <%@ include file="graphObserver.js"%>
    <%@ include file="script.js" %>
</script>

</html>