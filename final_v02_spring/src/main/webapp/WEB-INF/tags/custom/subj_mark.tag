<%@ attribute name="item" type="com.my.pet.spring.domain.Mark" required="true" %>

<td>${item.getSubjName()}</td>
<td><input type="number" min=0 max=12 name="id${item.getId()}" value="${item.getValue()}"></td>