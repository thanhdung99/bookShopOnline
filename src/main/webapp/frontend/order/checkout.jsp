<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <title>Shopping cart</title>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="shortcut icon" href="">
    <jsp:include page="/importCss.jsp" />
    <link rel="stylesheet" href="/css/cart_order.css">

</head>

<body>

<%@include file="/components/Header.jsp"%>
<div class="container p-0">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-8 text-center">
                <div class="check-out-header border p-2 mb-1">
                    <h3>Order Details</h3>
                    <!-- <hr> -->
                </div>
                <table class="table table-bordered table-striped table-hover table-responsive-lg">
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
                    <tbody class="text-center">
                    <c:forEach items="${cart.items}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td> <!-- / .Product number - auto increase-->
                            <td class="text-left">
                                <div class="cart-info d-flex flex-nowrap">
                                    <img src="data:image/jpg;base64,${item.key.base64Image}" alt="Effective Java">
                                    <!-- / .Book image-->
                                    <p class="m-0 p-2 col-12 text-truncate" style="max-width: 10rem;">
                                            ${item.key.title}
                                    </p>
                                    <!-- / .Book name-->
                                </div>
                            </td>
                            <td>${item.key.author}</td> <!-- / .Author's name-->
                            <td><fmt:formatNumber value="${item.key.price * item.value}" type="currency"/></td> <!-- / .Unit price-->
                            <td>${item.value}</td> <!-- / .Quantity-->
                            <td><fmt:formatNumber value="${item.key.price * item.value}" type="currency"/></td> <!-- / .Subtotal price-->
                        </tr>
                    </c:forEach>
                    <!-- / .Render cart data -->
                    </tbody>
                    <tfoot class="text-center">
                    <tr>
                        <td colspan="3"></td>
                        <td class="text-uppercase"><b>Total:</b></td>
                        <td>${cart.totalQuantity}</td> <!-- / .Sum of quantities-->
                        <td><fmt:formatNumber value="${cart.totalAmount}" type="currency"/></td> <!-- / .Sum of subtotal-->
                    </tr>
                    </tfoot>
                </table>
                <div class="row">
                    <div class="col-md-4 col-lg-3 m-auto">
                        <a href="${pageContext.request.contextPath}/view_cart"><button class="btn btn-danger btn-block">Edit Cart</button></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 text-center py-2 check-out border">
                <h3>Shopping Information</h3>
                <hr>
                <form>
                    <div class="form-group">
                        <label for="inputName" class="font-italic">Full Name</label>
                        <input type="text" class="form-control form-control-custom" id="inputName"
                               placeholder="Full name...">
                    </div>
                    <div class="form-group">
                        <label for="inputPhone" class="font-italic">Contact Number</label>
                        <input type="text" class="form-control form-control-custom" id="inputPhone"
                               placeholder="Phone...">
                    </div>
                    <div class="form-group">
                        <label for="inputAddress" class="font-italic">Address</label>
                        <input type="text" class="form-control form-control-custom" id="inputAddress"
                               placeholder="Apartment, studio, or floor...">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputCity" class="font-italic">City</label>
                            <input type="text" class="form-control form-control-custom" id="inputCity"
                                   placeholder="City...">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputZip" class="font-italic">Zip</label>
                            <input type="text" class="form-control form-control-custom" id="inputZip"
                                   placeholder="Zip code...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCountry" class="font-italic">Country</label>
                        <input type="text" class="form-control form-control-custom" id="inputCountry"
                               placeholder="Country...">
                    </div>
                    <hr>
                    <div class="form-group">
                        <h5 class="font-italic">Payment method</h5>
                        <div class="icon-groups-custom border-bottom pb-2 mb-2">
                            <span class="font-italic mr-2">Accepted Cards:</span>
                            <i class="fab fa-cc-visa" style="color: navy"></i>
                            <i class="fab fa-cc-paypal" style="color: darkturquoise"></i>
                            <i class="fab fa-cc-mastercard" style="color: red"></i>
                        </div>
                        <select id="inputPayment"
                                class="form-control form-control-custom font-weight-bold mb-3">
                            <option value="default" class="option-size-custom" selected>Cash on delivery
                            </option>
                            <option value="visa" class="option-size-custom">Visa</option>
                            <option value="paypal" class="option-size-custom">PayPal</option>
                            <option value="mastercard" class="option-size-custom">MasterCard</option>
                        </select>
                        <div class="js__credit-card d-none">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="datetimepicker" class="font-italic">Exp Day</label>
                                    <div class="input-group date" data-provide="datepicker"
                                         id="datetimepicker">
                                        <input type="text" class="form-control form-control-custom">
                                        <div class="input-group-addon text-center">
                                                            <span class="input-group-text bg-white calendar-custom">
                                                                <i class="far fa-calendar-alt"></i>
                                                            </span>
                                        </div>
                                    </div>
                                    <!-- <input type="text" class="form-control form-control-custom" id="inputExpDay"
                                    placeholder="dd/mm/yyyy..."> -->
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="inputCVV" class="font-italic">CVV</label>
                                    <input type="text" class="form-control form-control-custom"
                                           id="inputCVV" placeholder="CVV...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCardNumber" class="font-italic">Credit Card
                                    Number</label>
                                <input type="text" class="form-control form-control-custom"
                                       id="inputCardNumber" placeholder="1111-2222-3333-4444...">
                            </div>
                        </div>
                    </div>
                </form> <!-- / .Render user info to this form-->
                <div class="row">
                    <div class="col-md-6 col-lg-5 pb-2">
                        <button type="button" class="btn btn-danger btn-block">Order</button>
                    </div>
                    <div class="col-md-6 col-lg-7 pb-2">
                        <a href="${pageContext.request.contextPath}/">
                            <button type="button" class="btn btn-outline-danger btn-block">Continue Shopping</button>
                        </a>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/importLib.jsp"%>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#datetimepicker").datepicker();
    });
</script> <!-- / .Date time picker-->
<script>
    $(document).ready(function () {
        $("#inputPayment").on("change", () => {
            const selectedValue = document.querySelector("#inputPayment").value;
            if (selectedValue !== "default") {
                $(".js__credit-card").removeClass("d-none");
            } else {
                $(".js__credit-card").addClass("d-none");
            }
        })
    });
</script> <!-- / .Toggle credit card-info form-->

<c:if test="${message != null}">
    <jsp:include page="/frontend/toast.jsp"/>
</c:if>

<%@include file="/components/Footer.jsp"%>


</body>

</html>