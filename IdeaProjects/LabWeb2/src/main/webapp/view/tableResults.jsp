<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="table">
    <table>
        <thead>
        <td class="top left">x</td>
        <td class="top">y</td>
        <td class="top">R</td>
        <td class="top right">Попадание</td>
        </thead>

        <tfoot>
        <td class="left bot"></td>
        <td class="bot"></td>
        <td class="bot"></td>
        <td class="right bot"></td>
        </tfoot>

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
    </table>
    <a href="controller">Go back</a>

</div>

</body>
<style>

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
</style>
<script type="text/javascript" charset="UTF-8">
</script>
</html>
