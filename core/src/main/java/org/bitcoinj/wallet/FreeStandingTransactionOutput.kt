package org.bitcoinj.wallet

import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.core.TransactionOutput
import org.bitcoinj.core.UTXO

/**
 * A custom [TransactionOutput] that is free standing. This contains all the information
 * required for spending without actually having all the linked data (i.e parent tx).
 *
 */
class FreeStandingTransactionOutput constructor(params: NetworkParameters, val utxo: UTXO,
                                                private val chainHeight: Int)
    : TransactionOutput(params, null, utxo.value, utxo.script.program) {

    /**
     * Get the depth withing the chain of the parent tx, depth is 1 if it the output height is the height of
     * the latest block.
     * @return The depth.
     */
    override fun getParentTransactionDepthInBlocks(): Int {
        return chainHeight - utxo.height + 1
    }

    override fun getIndex(): Int {
        return utxo.index.toInt()
    }

    override fun getParentTransactionHash(): Sha256Hash {
        return utxo.hash
    }
}