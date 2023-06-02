| No. | Test description |
|:----------:|:-------------|
| 1 | When firing in SINGLE mode, first the primary, then the secondary store is fired. |
| 2 | If the primary store is empty, the secondary tries to fire. |
| 3 | If both stores are empty, the ship doesn't fire. |
| 4 | When at least one store fired, the method returns true. |
| 5 | When neither one of the stores fired, the methods returns false. |
| 6 | When at least one store is empty, firing in ALL mode fails. |
| 7 | When primary store was fired last and secondary is empty, fire the primary again. |
| 8 | When primary store was fired last and secondary is empty, fire the primary again, which is empty, so fail. |
| 9 | Firing in ALL mode, when neither is empty and successfully fires. |
| 10 | Firing in ALL mode, when both are empty returns false.  |
| 11 | Firing in ALL mode, when only first succeeds returns false. |
| 12 | Firing in ALL mode, when only second succeeds returns false. |
