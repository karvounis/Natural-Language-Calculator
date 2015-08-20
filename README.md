# Natural-Language-Calculator
This text-based console application is performing simple natural calculations.
If the user enters "five times three" the app will output '15'.

#Guidelines:
<ol>
<li>Input values are:</li>
<ul>
<li>Whole numbers: [0,9]</li><li>Expressed as text.</li><li>Not case-sensitive.</li>
</ul>

<li>Arithmetic operations supported are not case-sensitive and are displayed in the following table: </li>
<table>
<tr><td>Operator</td>	<td>Permitted Aliases</td></tr>
<tr><td>Add (+)</td>	<td>add, plus</td></tr>
<tr><td>Subtract (-)</td>	<td>subtract, minus, less</td></tr>
<tr><td>Multiply (*)</td>	<td>multiply-by, times</td></tr>
<tr><td>Divide (/)	</td><td>divide-by, over</td></tr>
</table>

<li>Any number of operations are chained together.</li>

<li>When two or more operations are chained together, any multiply or divide operation take precedence over any add or subtract operation, similar to how a real calculator works. 
However, no support exists for use of brackets to alter operator precedence</li>

</ol>


This calculator was implemented as an interview assignment.
