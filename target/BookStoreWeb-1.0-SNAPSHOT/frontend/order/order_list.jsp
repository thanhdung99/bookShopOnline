<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <title>Categories Search</title>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="shortcut icon" href="">
    <jsp:include page="/importCss.jsp" />
    <link rel="stylesheet" href="/css/profile.css">

</head>
<body>
<%@include file="/components/Header.jsp"%>

<div class="container pb-3">
    <div class="table-responsive-lg">
        <table class="table table-bordered table-striped table-hover">
            <thead class="thead-dark">
            <tr>
                <th>No.</th>
                <th>Order ID</th>
                <th>Quantity</th>
                <th>Total Amount</th>
                <th>Order Date</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <!-- <td>${status.index + 1}</td> -->
                <td>1</td>
                <!-- / .Product number - auto increase-->

                <!-- <td>${cart.id}</td> -->
                <td>666</td>
                <!-- / .Order ID -->

                <td>
                    <!-- <span class="number-custom font-weight-bold">${cart.totalQuantity}</span> -->
                    <span class="font-weight-bold">1</span>
                </td> <!-- / .Number of items-->

                <td>
                    <!-- <fmt:formatNumber value="${cart.totalAmount}" type="currency" /> -->
                    <span>$999</span>
                </td> <!-- / .Total price-->

                <td>
                    <!-- <span>${cart.orderDate}</span> -->
                    <span>31/12/2020</span>
                </td> <!-- / .Order Date-->
                <td>
                    <!-- <span>${cart.status}</span> -->
                    <span>Completed</span>
                </td> <!-- / .Order Status-->
                <td>
                    <a href="#" class="btn btn-danger btn-sm">View Details</a>
                    <!-- / .View order details-->
                </td>
            </tr>
            </tbody>
            <tfoot>
            </tfoot>
        </table>
    </div>
</div>

<%@include file="/components/Footer.jsp"%>
<jsp:include page="/importLib.jsp"/>


</body>
</html>