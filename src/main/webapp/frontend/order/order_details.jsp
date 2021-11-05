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

<div class="container pb-3 d-flex justify-content-center flex-wrap">
    <div class="pb-5 pt-3 col-10">
        <h2 class="text-center">Invoice</h2>
        <section class="row mt-5 store-user">
            <div class="col-1"></div>
            <div class="col-10">
                <div class="row bb pb-3 d-flex justify-content-center">
                    <div class="col-7">
                        <p>Supplier,</p>
                        <h3 class="text-info">Group 06</h3>
                        <p class="address">01 - Vo Van Ngan,
                            <br>Thu Duc, HCM City,
                            <br>Vietnamese
                        </p>
                        <div class="phone mt-2">+84 361323695</div>
                    </div>
                    <div class="col-5">
                        <p>Recipient,</p>
                        <h3 class="text-info">Customer name</h3>
                        <p class="address">999 Bridgeton,
                            <br>St. Louis, Missouri,
                            <br>United States
                        </p>
                        <div class="phone mt-2">+84 123456789</div>
                    </div>
                </div>
                <hr>
                <div class="row extra-info pt-3">
                    <div class="col-7">
                        <p>Order ID:
                            <span>82</span>
                        </p>
                        <p>Order Date:
                            <span>12/12/2020</span>
                        </p>
                        <p>Order Status:
                            <span>Completed</span>
                        </p>
                    </div>
                    <div class="col-5">
                        <p>Payment Method:
                            <span>PayPal</span>
                        </p>
                        <p>Quantity:
                            <span>1</span>
                        </p>
                        <p>Total Price:
                            <span>$99.99</span>
                        </p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div class="table-responsive-lg col-10">
        <table class="table table-bordered table-striped table-hover text-center">
            <thead class="thead-dark">
            <tr>
                <th>No.</th>
                <th>Book</th>
                <th>Author</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td> <!-- / .Product number - auto increase-->
                <td>
                    <div class="cart-info">
                        <img src="../../assets/img/150x169/img1.png" alt="Effective Java">
                        <!-- / .Book image-->
                        <p class="m-0 p-2 col-12 text-truncate" style="max-width: 10rem;">Effective
                            Java
                        </p>
                        <!-- / .Book name-->
                    </div>
                </td>
                <td>
                    Author's name
                </td>
                <td>
                    $99.99
                </td> <!-- / .Unit price-->
                <td>
                    1
                </td> <!-- / .Quantity-->
                <td>
                    $99.99
                    <!-- / .Subtotal-->
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3"></td>
                <td class="text-uppercase"><b>Total:</b></td>
                <td>1 book(s)</td> <!-- / .Sum of quantities-->
                <td>
                    <fmt:formatNumber value="${cart.totalAmount}" type="currency" />
                    <span>$99.99</span>
                </td> <!-- / .Sum of subtotal-->
            </tr>
            </tfoot>
        </table>
    </div>
</div>

<%@include file="/components/Footer.jsp"%>
<jsp:include page="/importLib.jsp"/>


</body>
</html>