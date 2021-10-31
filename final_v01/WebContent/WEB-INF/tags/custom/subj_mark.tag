<%@ attribute name="item" type="entity.Mark" required="true" %>

<td>${item.getSubjName()}</td>
<td><input type="number" min=0 max=12 name="id${item.getId()}" value="${item.getValue()}"></td>