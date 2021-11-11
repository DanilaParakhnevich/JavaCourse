<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.getSession().setAttribute("command", "main_page");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>Result</title>

        <style type="text/css">
            TABLE {
                width: 300px; /* Ширина таблицы */
                border-collapse: collapse; /* Убираем двойные линии между ячейками */
            }
            TD, TH {
                padding: 3px; /* Поля вокруг содержимого таблицы */
                border: 1px solid black; /* Параметры рамки */
            }
            TH {
                background: white; /* Цвет фона */

            }
            TD {
                background: floralwhite;
            }
            TH {
                text-decoration-color: crimson;
            }
            body {background: #c7b39b url(https://oir.mobi/uploads/posts/2021-03/1616542062_4-p-chernii-tsvet-fon-4.jpg)}
        </style>
    </head>
    <body  vlink="#cecece">
        <a href="${pageContext.request.contextPath}/jsp/main.jsp">
            Back to main page
        </a>
        <h1 align="center">Result:</h1>
        <table align="center">
            <tr>
                <th>Country</th>
                <th>Owner</th>
                <th>Deposit</th>
            </tr>
            <c:forEach var="bank" items="${bankList}">
            <tr>
                <td>${bank.country}</td>
                <td>
                    <c:if test="${bank.isCommercial()}" >
                        ${bank.owner}
                    </c:if>
                    <c:if test="${!bank.isCommercial()}" >
                        -
                    </c:if>
                </td>
                <td>
                    <table>
                        <tr>
                            <th>Id</th>
                            <th>Type</th>
                            <th>Investment</th>
                            <th>Profitability</th>
                            <th>Time</th>
                            <th>Depositor's name</th>
                            <th>Depositor's account id</th>
                        </tr>
                <c:forEach var="deposit" items="${bank.depositList}">
                    <tr>
                        <td>${deposit.id}</td>
                        <td>${deposit.type}</td>
                        <td>${deposit.investment}</td>
                        <td>${deposit.profitability}</td>
                        <td>${deposit.timeBegin} - ${deposit.timeEnd}</td>
                        <td>${deposit.depositor.name}</td>
                        <td>${deposit.depositor.accountId}</td>
                    </tr>
                </c:forEach>
                </table>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>