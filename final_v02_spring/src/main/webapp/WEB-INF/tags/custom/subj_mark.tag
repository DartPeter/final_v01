<%@ attribute name="item" type="com.my.pet.spring.dto.MarkDto" required="true" %>

<td>${item.getSubjectName()}</td>
<td><input type="number" min=0 max=12 name="id${item.getId()}" value="${item.getValue()}"></td>