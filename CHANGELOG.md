## Change
- sync from 1.21-0.21.1-sakura.1
  - add raw NBT support for inventoryOverlay/Preview code from Snapshot, and the related "Library" of NBT helper functions listed under BlockUtils and EntityUtils, along with Fully NBT compliant getNbtItems(), and getNbtInventory() functions under InventoryUtils.
  - This also adds a new InventoryOverlay.Context as a place holder Object for InventoryOverlay data.
  - add "locked slots" rendering support for inventoryPreview for Crafters
